package victor.training.cleancode;

import java.util.ArrayList;
import java.util.List;


public class UtilsVsVO {

    // Autovit.ro
    public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {
        Interval criteriaInterval = new Interval(criteria.getStartYear(), criteria.getEndYear());
        List<CarModel> results = new ArrayList<>(models);
        results.removeIf(model -> !model.getYearInterval().intersects(criteriaInterval));
        System.out.println("More filtering logic");
        return results;
    }
}
class Alta {
    {
        new Interval(1, 3).intersects(new Interval(2, 4));
    }
}
class Interval  {
    private final int start;
    private final int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public boolean intersects(Interval interval2) {
        // http://world.std.com/~swmcd/steven/tech/interval.html
        return start <= interval2.end && interval2.start <= end;
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

    public Interval getYearInterval() {
        return new Interval(startYear, endYear);
    }
}