package cleancode;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class UtilsVsVO {
    public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {
        List<CarModel> results = new ArrayList<>(models);
        results.removeIf(model -> ! MateUtil.intervalsIntersect(
                new Interval(model.getStartYear(), model.getEndYear()),
                new Interval(criteria.getStartYear(), criteria.getEndYear())));
        System.out.println("More filtering logic");
        return results;
    }
}

@Data
class Interval {
    private final int start;
    private final int end;
}

class MateUtil {
    // http://world.std.com/~swmcd/steven/tech/interval.html
    public static boolean intervalsIntersect(Interval a, Interval b) {
        return a.getStart() <= b.getEnd() && b.getStart() <= a.getEnd();
    }
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