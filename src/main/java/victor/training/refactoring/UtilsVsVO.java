package victor.training.refactoring;

import java.util.ArrayList;
import java.util.List;

public class UtilsVsVO {
    public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {
        List<CarModel> results = new ArrayList<>(models);
        results.removeIf(model -> ! model.getYearInterval().intersects(criteria.getYearInteval()));
        System.out.println("More filtering logic");
        return results;
    }
}

class Interval {
    private final int start;
    private final int end;
    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
    public boolean intersects(Interval other) {
        // http://world.std.com/~swmcd/steven/tech/interval.html
        return start <= other.end && other.start <= end;
    }
}
class MathUtil {

}


class Other2 {
    {
        new Interval(1, 3).intersects(new Interval(2, 4));

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

    public Interval getYearInteval() {
        return new Interval(startYear, endYear);
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