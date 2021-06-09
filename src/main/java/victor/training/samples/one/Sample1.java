package victor.training.samples.one;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.Future;

class AccountLists {
   private final List<Account> startAccounts;
   private final List<Account> performanceAccounts;
   private final List<Account> accountsWithInflightOrders;
   private final List<Account> liquidityAccounts;
   private final List<Account> securityDepositAccounts;

   AccountLists(List<Account> startAccounts, List<Account> performanceAccounts, List<Account> accountsWithInflightOrders, List<Account> liquidityAccounts, List<Account> securityDepositAccounts) {
      this.startAccounts = startAccounts;
      this.performanceAccounts = performanceAccounts;
      this.accountsWithInflightOrders = accountsWithInflightOrders;
      this.liquidityAccounts = liquidityAccounts;
      this.securityDepositAccounts = securityDepositAccounts;
   }

   public List<Account> getAccountsWithInflightOrders() {
      return accountsWithInflightOrders;
   }

   public List<Account> getPerformanceAccounts() {
      return performanceAccounts;
   }

   public List<Account> getLiquidityAccounts() {
      return liquidityAccounts;
   }

   public List<Account> getSecurityDepositAccounts() {
      return securityDepositAccounts;
   }

   public List<Account> getStartAccounts() {
      return startAccounts;
   }
}

public class Sample1 {
   private static final Logger LOGGER = LoggerFactory.getLogger(Sample1.class);
   private static final String ISO_8601_DATE_FORMAT = "FORMAT";
   private static final String FIRST_AUGUST_2016 = "";
   public static final Date START_BALANCE_DATE = U4RDateUtil.getStringAsDate(FIRST_AUGUST_2016, ISO_8601_DATE_FORMAT);
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
      // Orice ai pe un ThreadLocal se poate PROPAGA pe orice metode chemat cu ajutorul ThreadLocalTaskExecutor.setTaskDecorator

      Date startDate = dataset.getStartDate().toDate();

      String contractNo = contract != null ? String.valueOf(contract.getContractNumber()) : null;

      victor.training.samples.one.other.ReportInstrumentScopeType instrumentTypePerf = victor.training.samples.one.other.ReportInstrumentScopeType.ALL;

      Date effectiveEndDate = getEffectiveEndDate(rodCode, contractNo, dataset, finalAccounts);

      DateInterval dateInterval = new DateInterval(startDate, effectiveEndDate);

      dataset.setLiquidityMovementsDateMap(getCashDateMap(dataset.getPerfPeriods(), dateInterval));


      List<Date> cashDateList = Arrays.asList(effectiveEndDate);

      AccountLists accountLists = buildAccountLists(finalAccounts, true, dateInterval);
      List<Account> startAccounts = accountLists.getStartAccounts();
      List<Account> performanceAccounts = accountLists.getPerformanceAccounts();

      List<Account> accountsWithInflightOrders = accountLists.getAccountsWithInflightOrders();
      List<Account> liquidityAccounts = accountLists.getLiquidityAccounts();
      List<Account> securityDepositAccounts = accountLists.getSecurityDepositAccounts();

      // Start the Futures to retrieve all the informations at start date,
      // because
      // the view contains data at T-1, but user could select another end date
      Future<List<Account>> finalAccountsFuture = accountRepoAsync.addBalancesAndProducts(ctx, rodCode, finalAccounts,
          effectiveEndDate, contractNo, ReportInstrumentScopeType.ALL);
      // Start the Futures to retrieve all the informations at start date
      Future<List<Account>> startAccountsFuture = accountRepoAsync.addBalancesAndProducts(ctx, rodCode, startAccounts,
          startDate, contractNo, ReportInstrumentScopeType.ALL);
      // Start the Futures to retrieve inflight orders at start date and end

      // date
      Future<List<AccountFlightOrder>> startAccountsFlightOrdersFuture = null;
      Future<List<AccountFlightOrder>> endAccountsFlightOrdersFuture = null;
      if (!accountsWithInflightOrders.isEmpty()) {
         if (startDate.after(START_BALANCE_DATE)) {
            startAccountsFlightOrdersFuture = currentOrderRepoAsync.findInflightOrders(ctx, startDate,
                accountsWithInflightOrders, rodCode, contractNo, ReportInstrumentScopeType.ALL);
         }
         if (effectiveEndDate.after(START_BALANCE_DATE)) {
            endAccountsFlightOrdersFuture = currentOrderRepoAsync.findInflightOrders(ctx, effectiveEndDate,
                accountsWithInflightOrders, rodCode, contractNo, ReportInstrumentScopeType.ALL);
         }
         // Start the Future to retrieve investments and withdrawals
         Future<List<AccountMovements>> investmentsWithdrawalsFuture = movementsRepoAsync
             .findInvestmentWithdrawalsRealCashflow(ctx, rodCode, finalAccounts, startDate, effectiveEndDate, contractNo,
                 ReportInstrumentScopeType.ALL);
         // Start the Future to retrieve liquidity movements between N periods

         Date startDatePlusOneDay = DateUtils.addDays(startDate, 1);
         Future<List<AccountMovements>> liquidityAccountMovementsFuture = movementsRepoAsync
             .findAccountMovementsRealCashflow(ctx, rodCode, liquidityAccounts, startDatePlusOneDay, cashDateList,
                 contractNo, ReportInstrumentScopeType.ALL);
         // Start the Future to retrieve portfolio incomes, aka coupon and
         // dividends

         Future<List<AccountCouponDividend>> portfolioIncomesFuture = null;
         if (!securityDepositAccounts.isEmpty()) {
            portfolioIncomesFuture = portfolioIncomeRepoAsync.findCouponDividends(ctx, securityDepositAccounts,
                startDate, startDate, rodCode, contractNo, ReportInstrumentScopeType.ALL);
         }

         // request for virtual CashFlow
      Future<List<AccountBalance>> balancesOfAccounts = accountRepoAsync.findVirtualCashflow(ctx, rodCode,
          liquidityAccounts, startDate, effectiveEndDate, contractNo, ReportInstrumentScopeType.ALL);
//         Future<List<AccountBalance>> balancesOfAccounts = accountRepoAsync.findVirtualCashflow(
//             rodContract, liquidityAccounts, dateInterval);

         Future<ClassifiedPortfolioPerformance> acClassificationPerformanceFuture = null;
         if (!performanceAccounts.isEmpty()) {
            // Start the Future to retrieve Asset Class classification at
            // start date and end
            // date with relative performancessa n
            acClassificationPerformanceFuture = assetClassRepoAsync.calculatePortfolioAndAccountPerformance(ctx,
                startDate, effectiveEndDate, performanceAccounts, rodCode, contractNo, instrumentTypePerf);

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

            // ne imaginam ca in met de mai sus se atribuie pe dataset minim 5 campuri.
//            Daca asa e:
            // dataset.investmentWithdrawals = AsyncUtils.getResultFromAsyncTask(investmentsWithdrawalsFuture);

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
               ClassifiedPortfolioPerformance acClassPerf = AsyncUtils.getResultFromAsyncTask(acClassificationPerformanceFuture);
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
                  ClassifiedPortfolioPerformance performanceWithLiquidities = acClassPerf.copy();
                  dataset.setAcClassificationPerformancesWithLiquidity(performanceWithLiquidities);
                  // Add cash accounts classification at start date

                  CashAccountsClassification cashClassification = new CashAccountsClassification(startDate,
                      dataset.getStartCashAccounts().getAccounts(), dataset.getCurrencyMap());
                  performanceWithLiquidities.getAssetPerformances().addCashClassification(cashClassification, false, startIfo);
                  // Add cash accounts classification at end date

                  cashClassification = new CashAccountsClassification(effectiveEndDate, dataset.getFinalCashAccounts().getAccounts(), dataset.getCurrencyMap());
                  performanceWithLiquidities.getAssetPerformances().addCashClassification(cashClassification, true, endIfo);
                  // Balances retrieved from Balance ws, should be aligned
                  // with the totals
                  // retrieved from the Asset Class Classification ws. The
                  // method will fix the
                  // misleading error if present, till 2 EUR.
                  ClassificationPerformanceUtil.calculatePerformanceTotals(performanceWithLiquidities,
                      dataset.getStartAccounts().getTotalCtv(), dataset.getFinalAccounts().getTotalCtv());
                  // Calculate the percentages of classification
                  dataset.getAcClassificationPerformancesWithLiquidity().updateWithInflightOrders(startIfo, endIfo);
                  performanceWithLiquidities.calculatePercentage();

                  // Dump the Asset Class classifications and performances

                  if (performanceWithLiquidities != null && contractNo == null) {
                     assetClassRepo.save(performanceWithLiquidities, startDate, effectiveEndDate, rodCode);
                  }

                  LOGGER.debug("DATASET AC ORIGINAL {}", acClassPerf);
                  LOGGER.debug("DATASET AC MODIFIED {}", dataset.getAcClassificationPerformancesWithLiquidity());
               }
            }

            setAccountsDescription(dataset);
         } catch (Exception e) {

//       } finally {
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
   }
   private AccountLists buildAccountLists(List<Account> finalAccounts, boolean b, DateInterval dateInterval) {
      return null;
   }

   private Map<Date, String> getCashDateMap(Long perfPeriod, DateInterval dateInterval) {
      try {
         RequestContext ctx = RequestContextThreadLocal.getRequestContext();
         return utilRepo.calculateDates(dateInterval, ctx.getUserLocale(), perfPeriod.intValue());
      } catch (Exception e) {
         throw new GALException(MessageCode.UNEXPECTED, e);
      }
   }

   private Date getEffectiveEndDate(Long rodCode, String contractNo, DefaultDataSet dataset, List<Account> finalAccounts) {
      Date startDate = dataset.getStartDate().toDate();
      Date userEndDate = dataset.getEndDate().toDate();
      DateInterval dateInterval = new DateInterval(startDate, userEndDate);
      // Temporary fix for MIP
      ClassifiedPortfolioPerformance classifiedPortofolioPerformanceTemp = assetClassRepo
          .calculatePortfolioAndAccountPerformanceTemp(dateInterval, finalAccounts, rodCode, contractNo, victor.training.samples.one.other.ReportInstrumentScopeType.ALL);

      Date endDate2;
      if (classifiedPortofolioPerformanceTemp.hasWarning()) {
         endDate2 = classifiedPortofolioPerformanceTemp.getEndDate();
         dataset.setEndDate(LocalDate.fromDateFields(endDate2));
      } else {
         endDate2 = dateInterval.getEndDate();
      }
      return endDate2;
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
