package victor.training.refactoring;

import java.util.Date;

public class EncapsulateConditionals {
    public double getQuote(Date date, RatesPlan plan, int quantity, float clientFidelityFactor) {
        double charge;
        if (!(date.before(plan.getSummerStart())) && date.before(plan.getSummerEnd()))
            charge = quantity * plan.getSummerRate();
        else
            charge = quantity * plan.getRegularRate() + plan.getRegularServiceCharge();
        return charge - clientFidelityFactor;
    }
}


class RatesPlan {
    private final Date summerStart;
    private final Date summerEnd;
    private final double summerRate;
    private final double regularRate;
    private final double regularServiceCharge;

    RatesPlan(Date summerStart, Date summerEnd, double summerRate, double regularRate, double regularServiceCharge) {
        this.summerStart = summerStart;
        this.summerEnd = summerEnd;
        this.summerRate = summerRate;
        this.regularRate = regularRate;
        this.regularServiceCharge = regularServiceCharge;
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
