package victor.training.samples.one;

import org.joda.time.LocalDate;

import java.util.Date;
import java.util.List;
import java.util.Map;


public class DefaultDataSet {
   private LocalDate startDate;
   private LocalDate endDate;
   private Map<Date, String> liquidityMovementsDateMap;
   private boolean cashAccountSelected;
   private List<AccountFlightOrder> startAccountsFlightOrders;
   private List<AccountFlightOrder> endAccountsFlightOrders;
   private List<AccountCouponDividend> accountCouponAndDividends;
   private ClassifiedPortfolioPerformance acClassificationPerformances;
   private ClassifiedPortfolioPerformance acClassificationPerformancesWithLiquidity;
   private CashAccounts finalCashAccounts;
   private Accounts finalAccounts;

   public LocalDate getStartDate() {
      return startDate;
   }

   public void setStartDate(LocalDate startDate) {
      this.startDate = startDate;
   }

   public Long getPerfPeriods() {
      return null;
   }

   public LocalDate getEndDate() {
      return endDate;
   }

   public void setEndDate(LocalDate endDate) {
      this.endDate = endDate;
   }

   public void setLiquidityMovementsDateMap(Map<Date, String> liquidityMovementsDateMap) {
      this.liquidityMovementsDateMap = liquidityMovementsDateMap;
   }

   public Map<Date, String> getLiquidityMovementsDateMap() {
      return liquidityMovementsDateMap;
   }

   public void setCashAccountSelected(boolean cashAccountSelected) {
      this.cashAccountSelected = cashAccountSelected;
   }

   public boolean isCashAccountSelected() {
      return cashAccountSelected;
   }

   public <T> void setStartAccountsFlightOrders(List<AccountFlightOrder> startAccountsFlightOrders) {
      this.startAccountsFlightOrders = startAccountsFlightOrders;
   }

   public List<AccountFlightOrder> getStartAccountsFlightOrders() {
      return startAccountsFlightOrders;
   }

   public <T> void setEndAccountsFlightOrders(List<AccountFlightOrder> endAccountsFlightOrders) {
      this.endAccountsFlightOrders = endAccountsFlightOrders;
   }

   public List<AccountFlightOrder> getEndAccountsFlightOrders() {
      return endAccountsFlightOrders;
   }

   public <T> void setAccountCouponAndDividends(List<AccountCouponDividend> accountCouponAndDividends) {
      this.accountCouponAndDividends = accountCouponAndDividends;
   }

   public List<AccountCouponDividend> getAccountCouponAndDividends() {
      return accountCouponAndDividends;
   }

   public void setAcClassificationPerformances(ClassifiedPortfolioPerformance acClassificationPerformances) {
      this.acClassificationPerformances = acClassificationPerformances;
   }

   public ClassifiedPortfolioPerformance getAcClassificationPerformances() {
      return acClassificationPerformances;
   }

   public void setAcClassificationPerformancesWithLiquidity(ClassifiedPortfolioPerformance acClassificationPerformancesWithLiquidity) {
      this.acClassificationPerformancesWithLiquidity = acClassificationPerformancesWithLiquidity;
   }

   public ClassifiedPortfolioPerformance getAcClassificationPerformancesWithLiquidity() {
      return acClassificationPerformancesWithLiquidity;
   }

   public CashAccounts getStartCashAccounts() {
      return null;
   }

   public Map<Object, Object> getCurrencyMap() {
      return null;
   }

   public CashAccounts getFinalCashAccounts() {
      return finalCashAccounts;
   }

   public void setFinalCashAccounts(CashAccounts finalCashAccounts) {
      this.finalCashAccounts = finalCashAccounts;
   }

   public Accounts getStartAccounts() {
      return null;
   }

   public Accounts getFinalAccounts() {
      return finalAccounts;
   }

   public void setFinalAccounts(Accounts finalAccounts) {
      this.finalAccounts = finalAccounts;
   }
}
