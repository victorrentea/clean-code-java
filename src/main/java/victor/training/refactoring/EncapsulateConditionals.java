package victor.training.refactoring;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class EncapsulateConditionals {
   public double computeQuote(Date date, RatesPlan plan, int quantity, float clientFidelityFactor) {
      return computeBaseCharge(date, plan, quantity) - clientFidelityFactor;
   }

//   DAO dao;
   private double computeBaseCharge(Date date, RatesPlan plan, int quantity) {
//      dao.query()
      if (plan.isDuringSummer(date))
         return quantity * plan.getSummerRate();
      else
         return quantity * plan.getRegularRate() + plan.getRegularServiceCharge();
   }

}



class RatesPlan {
   private final Date summerStart;
   private final Date summerEnd;
   private final double summerRate;
   private final double regularRate;
   private final double regularServiceCharge;

   RatesPlan(Date summerStart, Date summerEnd, double summerRate, double regularRate, double regularServiceCharge) {
      if (summerStart.after(summerEnd)) {
         throw new IllegalArgumentException("summer date start > end");
      }
      this.summerStart = summerStart;
      this.summerEnd = summerEnd;
      this.summerRate = summerRate;
      this.regularRate = regularRate;
      this.regularServiceCharge = regularServiceCharge;
   }

//   public boolean shouldBeDisplayedGrayedOut() { // MVC violation: nu ai ce sa cauti in entitatea cu treburi de prezentar
//   public boolean isEnabled() {    } // OK

   public boolean isDuringSummer(Date date) {
      return !(date.before(summerStart)) && date.before(summerEnd);
   }

   public Date getSummerEnd() {
      return summerEnd;
   }

   public Date getSummerStart() {
      return summerStart;
   }

   public double getRegularRate() {
      return regularRate;
   }

   public double getRegularServiceCharge() {
      return regularServiceCharge;
   }

   public double getSummerRate() {
      return summerRate;
   }
}
