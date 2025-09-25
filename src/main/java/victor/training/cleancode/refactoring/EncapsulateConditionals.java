package victor.training.cleancode.refactoring;

import java.util.Date;

public class EncapsulateConditionals {
    public double getQuote(Date date, RatesPlan plan, int quantity, float clientFidelityFactor) {
        double charge;
      if (!date.before(plan.summerStart()) && date.before(plan.summerEnd())) {
        charge = quantity * plan.summerRate();
      } else {
        charge = quantity * plan.regularRate() + plan.regularServiceCharge();
      }
        return charge - clientFidelityFactor;
    }
}


record RatesPlan(Date summerStart,
                 Date summerEnd,
                 double summerRate,
                 double regularRate,
                 double regularServiceCharge) {
}
