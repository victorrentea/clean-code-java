package victor.training.samples.one;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.Future;

public class Sample1 {
   private static final Logger LOGGER = LoggerFactory.getLogger(Sample1.class);
   private static final String ISO_8601_DATE_FORMAT = "FORMAT";
   private static final String FIRST_AUGUST_2016 = "";
   private AssetClassRepo assetClassRepo;
   private UtilRepo utilRepo;
   private AccountRepoAsync accountRepoAsync;
   private PortfolioIncomeRepoAsync portfolioIncomeRepoAsync;
   private MovementsRepoAsync movementsRepoAsync;
   private AssetClassRepoAsync assetClassRepoAsync;
   private CurrentOrderRepoAsync currentOrderRepoAsync;
   private ProductRepository productRepository;


   private void loadAsyncContract(Long rodCode, DefaultDataSet dataset, List<Account> finalAccounts, Contract contract) {
      RequestContext ctx = RequestContextThreadLocal.getRequestContext();
      Date startDate = dataset.getStartDate().toDate();
      Date endDate = dataset.getEndDate().toDate();
      Date startDatePlusOneDay = DateUtils.addDays(startDate, 1);
      Map<Date, String> cashDateMap;
      List<Date> cashDateList;
      Long perfPeriod = dataset.getPerfPeriods();

      String contractNo = null;
      if (contract != null) {
         contractNo = String.valueOf(contract.getContractNumber());
      }
      ReportInstrumentScopeType instrumentType = ReportInstrumentScopeType.ALL;
      victor.training.samples.one.other.ReportInstrumentScopeType instrumentTypePerf = victor.training.samples.one.other.ReportInstrumentScopeType.ALL;

      // Temporary fix for MIP
      ClassifiedPortfolioPerformance classifiedPortofolioPerformanceTemp = assetClassRepo
          .calculatePortfolioAndAccountPerformanceTemp(startDate, endDate, finalAccounts, rodCode, contractNo,
              instrumentTypePerf);

      if (classifiedPortofolioPerformanceTemp.isHasWarning()) {
         endDate = classifiedPortofolioPerformanceTemp.getEndDate();
         dataset.setEndDate(LocalDate.fromDateFields(endDate));
      }

      try {
         cashDateMap = utilRepo.calculateDates(startDate, endDate, ctx.getUserLocale(), perfPeriod.intValue());
         dataset.setLiquidityMovementsDateMap(cashDateMap);

         cashDateList = Arrays.asList(endDate);

      } catch (Exception e) {
         throw new GALException(MessageCode.UNEXPECTED, e);
      }

      List<List<Account>> accountLists = buildAccountLists(finalAccounts, true, startDate, endDate);
      List<Account> startAccounts = accountLists.get(0);
      List<Account> performanceAccounts = accountLists.get(1);

      List<Account> accountsWithInflightOrders = accountLists.get(2);
      List<Account> liquidityAccounts = accountLists.get(3);
      List<Account> securityDepositAccounts = accountLists.get(4);

//      dataset.setNdgAccountsMap(ndgAccountsMap(finalAccounts)); FIXME

      // Start the Futures to retrieve all the informations at start date,
      // because
      // the view contains data at T-1, but user could select another end date
      Future<List<Account>> finalAccountsFuture = accountRepoAsync.addBalancesAndProducts(ctx, rodCode, finalAccounts,
          endDate, contractNo, instrumentType);
      // Start the Futures to retrieve all the informations at start date
      Future<List<Account>> startAccountsFuture = accountRepoAsync.addBalancesAndProducts(ctx, rodCode, startAccounts,
          startDate, contractNo, instrumentType);
      // Start the Futures to retrieve inflight orders at start date and end
      // date
      Future<List<AccountFlightOrder>> startAccountsFlightOrdersFuture = null;
      Future<List<AccountFlightOrder>> endAccountsFlightOrdersFuture = null;
      try {
         if (!accountsWithInflightOrders.isEmpty()) {
            if (startDate.after(U4RDateUtil.getStringAsDate(FIRST_AUGUST_2016, ISO_8601_DATE_FORMAT))) {
               startAccountsFlightOrdersFuture = currentOrderRepoAsync.findInflightOrders(ctx, startDate,
                   accountsWithInflightOrders, rodCode, contractNo, instrumentType);
            }
            if (endDate.after(U4RDateUtil.getStringAsDate(FIRST_AUGUST_2016, ISO_8601_DATE_FORMAT))) {
               endAccountsFlightOrdersFuture = currentOrderRepoAsync.findInflightOrders(ctx, endDate,
                   accountsWithInflightOrders, rodCode, contractNo, instrumentType);
            }
         }
      } catch (ParseException e) {
         LOGGER.debug(e.getMessage());
      }
      // Start the Future to retrieve investments and withdrawals
      Future<List<AccountMovements>> investmentsWithdrawalsFuture =  movementsRepoAsync
          .findInvestmentWithdrawalsRealCashflow(ctx, rodCode, finalAccounts, startDate, endDate, contractNo,
              instrumentType);
      // Start the Future to retrieve liquidity movements between N periods
      Future<List<AccountMovements>> liquidityAccountMovementsFuture = movementsRepoAsync
          .findAccountMovementsRealCashflow(ctx, rodCode, liquidityAccounts, startDatePlusOneDay, cashDateList,
              contractNo, instrumentType);
      // Start the Future to retrieve portfolio incomes, aka coupon and
      // dividends

      Future<List<AccountCouponDividend>> portfolioIncomesFuture = null;
      if (!securityDepositAccounts.isEmpty()) {
         portfolioIncomesFuture = portfolioIncomeRepoAsync.findCouponDividends(ctx, securityDepositAccounts,
             startDate, startDate, rodCode, contractNo, instrumentType);
      }

      // request for virtual CashFlow
      Future<List<AccountBalance>> balancesOfAccounts = accountRepoAsync.findVirtualCashflow(ctx, rodCode,
          liquidityAccounts, startDate, endDate, contractNo, instrumentType);

      Future<ClassifiedPortfolioPerformance> acClassificationPerformanceFuture = null;
      if (!performanceAccounts.isEmpty()) {
         // Start the Future to retrieve Asset Class classification at
         // start date and end
         // date with relative performancessa n
         acClassificationPerformanceFuture = assetClassRepoAsync.calculatePortfolioAndAccountPerformance(ctx,
             startDate, endDate, performanceAccounts, rodCode, contractNo, instrumentTypePerf);

      }

      // Begin the get of Futures to fill dataset
      try {
         // Update balances at end date
         setEndAccountsSituation(dataset, AsyncUtils.getResultFromAsyncTask(finalAccountsFuture));
         // Get Accounts balances, movements, flight orders and so on
         setStartAccountsSituation(dataset, AsyncUtils.getResultFromAsyncTask(startAccountsFuture));
         dataset.setCashAccountSelected(areCashAccountsSelected(dataset));

         if (!accountsWithInflightOrders.isEmpty() && dataset.isCashAccountSelected()) {
            dataset.setStartAccountsFlightOrders(
                AsyncUtils.getResultFromAsyncTask(startAccountsFlightOrdersFuture));
            LOGGER.debug("DATASET START INFLIGHT {}", dataset.getStartAccountsFlightOrders());
            dataset.setEndAccountsFlightOrders(AsyncUtils.getResultFromAsyncTask(endAccountsFlightOrdersFuture));
            LOGGER.debug("DATASET END INFLIGHT {}", dataset.getEndAccountsFlightOrders());
         }

         filterByCashAccountOfFlightOrders(dataset);

         setInvestmentsAndWithdrawals(dataset, AsyncUtils.getResultFromAsyncTask(investmentsWithdrawalsFuture));

         addVirtualCashflowToResult(dataset, AsyncUtils.getResultFromAsyncTask(balancesOfAccounts));

         setMovementsSituation(dataset, AsyncUtils.getResultFromAsyncTask(liquidityAccountMovementsFuture));

         if (!securityDepositAccounts.isEmpty()) {
            dataset.setAccountCouponAndDividends(AsyncUtils.getResultFromAsyncTask(portfolioIncomesFuture));
            Map<String, List<Product>> dividedProducts;
            for (Account account : securityDepositAccounts) {
               if (CollectionUtils.isNotEmpty(account.getProducts())) {
                  dividedProducts = productRepository.divideProducts(account.getProducts());
                  account.setDividedProducts(dividedProducts);
               }
            }
         }

         if (!performanceAccounts.isEmpty()) {
            // Get Asset Class classifications and performances
            // As normal case it contains only asset accounts data
            ClassifiedPortfolioPerformance acClassPerf = AsyncUtils
                .getResultFromAsyncTask(acClassificationPerformanceFuture);
            dataset.setAcClassificationPerformances(acClassPerf);

            if (acClassPerf != null) {
               BigDecimal startIfo = BigDecimal.ZERO;
               BigDecimal endIfo = BigDecimal.ZERO;
               if (dataset.isCashAccountSelected()) {
                  startIfo = computeTotalInflightOrder(dataset.getStartAccountsFlightOrders());
                  endIfo = computeTotalInflightOrder(dataset.getEndAccountsFlightOrders());
               }
               acClassPerf.calculatePercentage();

               // Build the Asset Class classifications and performances
               // object that consider
               // Monetary / Liquidity cash accounts counter values to the
               // relative Asset Class
               ClassifiedPortfolioPerformance acClassPerfWithLiq = acClassPerf.copy();
               dataset.setAcClassificationPerformancesWithLiquidity(acClassPerfWithLiq);
               // Add cash accounts classification at start date

               CashAccountsClassification cashClassification = new CashAccountsClassification(startDate,
                   dataset.getStartCashAccounts().getAccounts(), dataset.getCurrencyMap());
               acClassPerfWithLiq.getAssetPerformances().addCashClassification(cashClassification, false, startIfo);
               // Add cash accounts classification at end date

               cashClassification = new CashAccountsClassification(endDate, dataset.getFinalCashAccounts().getAccounts(), dataset.getCurrencyMap());
               acClassPerfWithLiq.getAssetPerformances().addCashClassification(cashClassification, true, endIfo);
               // Balances retrieved from Balance ws, should be aligned
               // with the totals
               // retrieved from the Asset Class Classification ws. The
               // method will fix the
               // misleading error if present, till 2 EUR.
               ClassificationPerformanceUtil.calculatePerformanceTotals(acClassPerfWithLiq,
                   dataset.getStartAccounts().getTotalCtv(), dataset.getFinalAccounts().getTotalCtv());
               // Calculate the percentages of classification
               dataset.getAcClassificationPerformancesWithLiquidity().updateWithInflightOrders(startIfo, endIfo);
               acClassPerfWithLiq.calculatePercentage();

               // Dump the Asset Class classifications and performances

               if (acClassPerfWithLiq != null && contractNo == null) {
                  assetClassRepo.save(acClassPerfWithLiq, startDate, endDate, rodCode);
               }

               LOGGER.debug("DATASET AC ORIGINAL {}", acClassPerf);
               LOGGER.debug("DATASET AC MODIFIED {}", dataset.getAcClassificationPerformancesWithLiquidity());
            }
         }

         setAccountsDescription(dataset);
      } catch (GALException e) {
         LOGGER.error(e.getMessage(), e);
         if (finalAccountsFuture != null) {
            U4RAsyncUtils.cancelExecution(finalAccountsFuture);
         }
         if (startAccountsFuture != null) {
            U4RAsyncUtils.cancelExecution(startAccountsFuture);
         }
         if (!accountsWithInflightOrders.isEmpty()) {
            if (startAccountsFlightOrdersFuture != null) {
               U4RAsyncUtils.cancelExecution(startAccountsFlightOrdersFuture);
            }
            if (endAccountsFlightOrdersFuture != null) {
               U4RAsyncUtils.cancelExecution(endAccountsFlightOrdersFuture);
            }
         }
         if (investmentsWithdrawalsFuture != null) {
            U4RAsyncUtils.cancelExecution(investmentsWithdrawalsFuture);
         }
         if (liquidityAccountMovementsFuture != null) {
            U4RAsyncUtils.cancelExecution(liquidityAccountMovementsFuture);
         }
         if (!securityDepositAccounts.isEmpty() && portfolioIncomesFuture != null) {
            U4RAsyncUtils.cancelExecution(portfolioIncomesFuture);
         }
         if (!performanceAccounts.isEmpty() && acClassificationPerformanceFuture != null) {
            U4RAsyncUtils.cancelExecution(acClassificationPerformanceFuture);
         }
      }
   }

   private void setAccountsDescription(DefaultDataSet dataset) {

   }

   private BigDecimal computeTotalInflightOrder(List<AccountFlightOrder> startAccountsFlightOrders) {
      return null;
   }

   private void setMovementsSituation(DefaultDataSet dataset, List<AccountMovements> resultFromAsyncTask) {
   }

   private void addVirtualCashflowToResult(DefaultDataSet dataset, List<AccountBalance> resultFromAsyncTask) {
   }

   private void setInvestmentsAndWithdrawals(DefaultDataSet dataset, List<AccountMovements> resultFromAsyncTask) {
   }

   private void filterByCashAccountOfFlightOrders(DefaultDataSet dataset) {

   }

   private boolean areCashAccountsSelected(DefaultDataSet dataset) {
      return false;
   }

   private void setStartAccountsSituation(DefaultDataSet dataset, List<Account> resultFromAsyncTask) {


   }

   private void setEndAccountsSituation(DefaultDataSet dataset, List<Account> resultFromAsyncTask) {

   }

   private List<List<Account>> buildAccountLists(List<Account> finalAccounts, boolean b, Date startDate, Date endDate) {
      return null;
   }
}
