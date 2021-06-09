package victor.training.samples.one;

import java.math.BigDecimal;
import java.util.Date;

public class ClassifiedPortfolioPerformance {
   private boolean hasWarning;

   public boolean hasWarning() {
      return hasWarning;
   }

   public void setHasWarning(boolean hasWarning) {
      this.hasWarning = hasWarning;
   }

   public Date getEndDate() {
      return null;
   }

   public ClassifiedPortfolioPerformance copy() {
      return null;
   }

   public void calculatePercentage() {

   }

   public AssetPerformances getAssetPerformances() {
      return null;
   }

   public void updateWithInflightOrders(BigDecimal startIfo, BigDecimal endIfo) {
   }
}
