package cleancode;

import java.util.ArrayList;
import java.util.List;

public class UtilsVsVO {
    public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {
        List<CarModel> results = new ArrayList<>(models);
        results.removeIf(model -> !
                new Interval(model.getStartYear(), model.getEndYear())
                        .intervalsIntersect(
                new Interval(criteria.getStartYear(), criteria.getEndYear())));
        System.out.println("More filtering logic");
        return results;
    }
}

class Interval {
    public final int start, end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    // http://world.std.com/~swmcd/steven/tech/interval.html
    public boolean intervalsIntersect(Interval other) {
        return this.start <= other.end && other.start <= this.end;
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
    private final int startYear;
    private final int endYear;

    public CarModel(int startYear, int endYear) {
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