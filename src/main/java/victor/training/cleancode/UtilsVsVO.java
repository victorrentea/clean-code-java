package victor.training.cleancode;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class UtilsVsVO {
    public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {
        List<CarModel> results = models.stream()
            .filter(model -> model.producedBetweenYears(criteria.getYearInterval()))
            .collect(toList());

        System.out.println("More filtering logic");
        return results;
    }
}
class Interval {
    private final int start;
    private final int end;

    public Interval(int start, int end) {
        if (start > end) {
            throw new IllegalArgumentException("start larger than end");
        }
        this.start = start;
        this.end = end;
    }

    public boolean intersects(Interval other) {
        return start <= other.end && other.start <= end;
    }
    public boolean doesntIntersect(Interval other) {
        return !intersects(other);
    }

}

class AltaClasa {
    public void method() {
        Interval interval1 = new Interval(1, 3);
        Interval interval2 = new Interval(2, 4);
        System.out.println(interval1.intersects(interval2));
    }
}


class CarSearchCriteria {
    private final Interval yearInterval;
    private final String make;

    public CarSearchCriteria(String make, Interval yearInterval) {
        this.make = make;
        this.yearInterval = yearInterval;
    }

    public String getMake() {
        return make;
    }

    public Interval getYearInterval() {
        return yearInterval;
    }
}

class CarModel {
    private final String make;
    private final String model;
    private final Interval yearInterval;

    public CarModel(String make, String model, Interval yearInterval) {
        this.make = make;
        this.model = model;
        this.yearInterval = yearInterval;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public boolean producedBetweenYears(Interval yearInterval) {
        return this.yearInterval.intersects(yearInterval);
    }
}