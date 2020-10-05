package victor.training.refactoring;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class UtilsVsVO {

    public static void main(String[] args) {
        CarSearchCriteria criteria = new CarSearchCriteria(new Interval(2006, 2016), "Ford");
        new UtilsVsVO().filterCarModels(criteria, Arrays.asList(
            new CarModel("Ford", "Focus", 2008, 2018)
        ));
    }

    public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {
        List<CarModel> results = models.stream()
            .filter(model -> model.producedDuring(criteria.getYearInterval()))
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
            throw new IllegalArgumentException();
        }
        this.start = start;
        this.end = end;
    }

    public boolean intersects(Interval other) {
        return start <= other.end && other.start <= end;
    }

}

class MathUtil {

    //    public static boolean intervalsIntersect(Interval interval1, Interval interval2) {
}






class CarSearchCriteria {
    private final Interval yearInterval;
    private final String make;

    public CarSearchCriteria(Interval yearInterval, String make) {
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
// Homework:
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

    public boolean producedDuring(Interval yearInterval) {
        return getYearInterval().intersects(yearInterval);
    }
}