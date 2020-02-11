package victor.training.refactoring;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

public class EncapsulateConditionals {
//    private static final Logger log = LoggerFactory.getLogger(EncapsulateConditionals.class);
//
//    static {
//        if (log.isTraceEnabled()) {
//            log.trace("Halo {}", fScumpa());
//        }
//    }
//
//    private static Object fScumpa() {
//        Thread.sleep(1000);
//        return "";
//    }

    // TODO encapsulate fields
    // TODO move method
    public double getQuote(Date date, RatesPlan plan, int quantity, float clientFidelityFactor)
    {
        double charge;
        if (plan.duringSummer(date)) {
            charge = summerCharge(plan, quantity);
        } else
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

    public void f() {

    }
}
