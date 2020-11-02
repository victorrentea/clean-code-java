package victor.training.cleancode;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class UtilsVsVO {

    // Ford Focus:     [2012 ---- 2016]
    // Search:              [2014 ---- 2018]

    public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {
        List<CarModel> results = models.stream()
            .filter(criteria::matchesProductionYears)
            .collect(toList());
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
        if (start > end) throw new IllegalArgumentException("start larger than end");
        this.start = start;
        this.end = end;
    }

    public boolean intersects(Interval other) {
        return start <= other.end && other.start <= end;
    }
}


class MathUtil {

}













class CodClient {
    public void method() {

        new CarSearchCriteria("Ford", new Interval(2007, 2010));
    }
}

class CarSearchCriteria {
    private final String make;
    private final Interval productionYears;

    public CarSearchCriteria(String make, Interval productionYears) {
        this.make = make;
        this.productionYears = productionYears;
    }

    public String getMake() {
        return make;
    }

    public boolean matchesProductionYears(CarModel model) {
        return productionYears.intersects(model.getProductionYears());
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

    public Interval getProductionYears() {
        return new Interval(startYear, endYear);
    }
}