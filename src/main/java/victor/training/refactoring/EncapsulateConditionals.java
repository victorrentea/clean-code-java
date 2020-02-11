package victor.training.refactoring;

import lombok.Data;

import java.util.Date;

public class EncapsulateConditionals {
    // TODO encapsulate fields
    // TODO move method
    public double getQuote(Date date, RatesPlan plan, int quantity, float clientFidelityFactor)
    {
        double charge;
        if (plan.duringSummer(date))
            charge = summerCharge(plan, quantity);
        else
            charge = offSeasonCharge(plan, quantity);
        return charge - clientFidelityFactor;
    }

    private double offSeasonCharge(RatesPlan plan, int quantity) {
        return quantity * plan.getRegularRate()  + plan.getRegularServiceCharge();
    }

    private double summerCharge(RatesPlan plan, int quantity) {
        return quantity * plan.getSummerRate();
    }

}


@Data
class RatesPlan {
    private final Date summerStart;
    private final Date summerEnd;
    private final double summerRate;
    private final double regularRate;
    private final double regularServiceCharge;

    public boolean duringSummer(Date date) {
        return !date.before(getSummerStart()) && date.before(getSummerEnd());
    }
}
