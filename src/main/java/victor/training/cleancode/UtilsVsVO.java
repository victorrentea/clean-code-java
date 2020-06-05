package victor.training.cleancode;

import java.util.ArrayList;
import java.util.List;

public class UtilsVsVO {
    public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {
        List<CarModel> results = new ArrayList<>(models);
        results.removeIf(model -> ! MathUtil.intervalsIntersect(
                model.getStartYear(), model.getEndYear(),
                criteria.getStartYear(), criteria.getEndYear()));
        System.out.println("More filtering logic");
        return results;
    }
}
//class Interval {
//    boolean intersects(Interval other) {}
//} // TODO tema de weekend folosind :
// refactorul : Convert to instance method (intelliJ)
class AlteClase {
    {
        MathUtil.intervalsIntersect(1, 2, 3, 4);
    }
}
class Constants {
    public static final String SPACE = " ";
}
class MathUtil {

    public static boolean intervalsIntersect(int start1, int end1, int start2, int end2) {
        // http://world.std.com/~swmcd/steven/tech/interval.html
        return start1 <= end2 && start2 <= end1;
    }
}





class CarSearchCriteria {
    private final int startYear;
    private final int endYear;
    private final String make;

    public CarSearchCriteria(int startYear, int endYear, String make) {
        this.make = make;
        if (startYear > endYear) throw new IllegalArgumentException("start larger than end");
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public int getStartYear() {
        return startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public String getMake() {
        return make;
    }
}

class CarModel {
    private final String make;
    private final String model;
    private final int startYear;
    private final int endYear;

    public CarModel(String make, String model, int startYear, int endYear) {
        this.make = make;
        this.model = model;
        if (startYear > endYear) throw new IllegalArgumentException("start larger than end");
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public int getStartYear() {
        return startYear;
    }
}