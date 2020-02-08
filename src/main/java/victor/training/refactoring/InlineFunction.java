package victor.training.refactoring;

public class InlineFunction {
    public static int getRating(Driver driver) {
        return moreThanFiveLateDeliveries(driver) ? 2 : 1;
    }

    public static boolean moreThanFiveLateDeliveries(Driver driver) {
        return driver.getNumberOfLateDeliveries() > 5;
    }
}

class Driver {

    public int getNumberOfLateDeliveries() {
        return 6;
    }
}
