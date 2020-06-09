package victor.training.refactoring;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EncapsulateConditionals {
    public double getQuote(Date date, RatesPlan plan, int quantity, float clientFidelityFactor) {

        List<Date> dates = Arrays.asList(new Date(), new Date(), new Date(), new Date());
        List<Date> summerDate = dates.stream().filter(plan::duringSummer).collect(Collectors.toList());

        double charge;
        if (plan.duringSummer(date)) {
            charge = quantity * plan.getSummerRate();
        } else {
            charge = quantity * plan.getRegularRate() + plan.getRegularServiceCharge();
        }
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

    public boolean duringSummer(Date date) {
        return !date.before(summerStart) && date.before(summerEnd);
    }
}
