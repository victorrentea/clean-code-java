package victor.training.cleancode;

import java.util.ArrayList;
import java.util.List;

public class UtilsVsVO {
    public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {
        List<CarModel> results = new ArrayList<>(models);
        Interval criteriaInterval = new Interval(criteria.getStartYear(), criteria.getEndYear());
//        Interval criteriaInterval = criteria.getYearInterval();
        results.removeIf(model -> !new Interval(model.getStartYear(), model.getEndYear()).intersects(
                criteriaInterval));
        System.out.println("More filtering logic");
        return results;
    }
}
class Interval {
    private final int start;
    private final int end;

    Interval(int start, int end) {
//        if  start >= end;
        this.start = start;
        this.end = end;
    }

    public boolean intersects(Interval other) {
        return start <= other.getEnd() && other.getStart() <= end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
class MathUtil {


}






class CarSearchCriteria {
    private final int startYear;
    private final int endYear;

    public CarSearchCriteria(int startYear, int endYear) {
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