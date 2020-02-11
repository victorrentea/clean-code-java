package victor.training.cleancode;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class UtilsVsVO {
    public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {
        List<CarModel> results = new ArrayList<>(models);
        // criteriaYearInterval = criteria.getYearInterval();
        Interval criteriaYearInterval = criteria.getYearInterval();
        results.removeIf(model -> !model.getYearInterval().intersects(criteriaYearInterval));
        System.out.println("More filtering logic");
        return results;
    }
}

@Data
class Interval {
    private final int start;
    private final int end;

    public Interval(int start, int end) {
        if (start > end) throw new IllegalArgumentException("start larger than end");
        this.start = start;
        this.end = end;
    }

    public boolean intersects(Interval other) {
        return start <= other.getEnd() && other.getStart() <= end;
    }
}


class CarSearchCriteria {
    private final Interval interval;
    private final int altceva;

    public CarSearchCriteria(int startYear, int endYear, int altceva) {
        this.altceva = altceva;
        interval = new Interval(startYear, endYear);
    }

    public Interval getYearInterval() {
        return interval;
    }
}

class CarModel {
    private final String make;
    private final String model;

    private final Interval interval;

    public CarModel(String make, String model, Interval interval) {
        this.make = make;
        this.model = model;
        this.interval = interval;
    }

    public Interval getYearInterval() {
        return interval;
    }
}