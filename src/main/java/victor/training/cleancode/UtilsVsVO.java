package victor.training.cleancode;

import java.util.List;
import java.util.stream.Collectors;

public class UtilsVsVO {

    // Ford Focus:     [2012 ---- 2016]
    // Search:              [2014 ---- 2018]

    public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {
        Interval criteriaInterval = new Interval(criteria.getStartYear(), criteria.getEndYear());
        List<CarModel> results = models.stream().filter(
            model -> new Interval(model.getStartYear(), model.getEndYear()).intersects(criteriaInterval))
            .collect(Collectors.toList());
        System.out.println("More filtering logic");
        return results;
    }
}

class Alta {
    public void method() {
        boolean ok = new Interval(1, 3).intersects(new Interval(2, 4));
        System.out.println(ok);
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
        return start <= other.end && other.start <= end;
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
}