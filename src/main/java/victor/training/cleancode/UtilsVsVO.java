package victor.training.cleancode;

import java.util.ArrayList;
import java.util.List;

public class UtilsVsVO {
    public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {
        List<CarModel> results = new ArrayList<>(models);
        Interval criteriaInterval = new Interval(criteria.getStartYear(), criteria.getEndYear());

        results.removeIf(model -> {
            Interval modelInterval = new Interval(model.getStartYear(), model.getEndYear());
            return !MathUtil.intervalsIntersect(modelInterval, criteriaInterval);
        });
        System.out.println("More filtering logic");
        return results;
    }
}

class AltColeg {
    {

        boolean b = MathUtil.intervalsIntersect(new Interval(1, 3), new Interval(2, 4));
        System.out.println(b);
    }
}
class Interval {
    private final int start;
    private final int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}

class MathUtil {

    public static boolean intervalsIntersect(Interval interval1, Interval interval2) {
        return interval1.getStart() <= interval2.getEnd() && interval2.getStart() <= interval1.getEnd();
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

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }
}