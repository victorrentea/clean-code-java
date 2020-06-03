package victor.training.cleancode;

import lombok.Data;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class SomeService {
    public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {
        List<CarModel> results = new ArrayList<>(models);
        results.removeIf(model -> ! model.getYearInterval().intervalsIntersect(criteria.getYearInterval()));
        System.out.println("More filtering logic");
        return results;
    }
}
class MathUtil {

}
// Java-hate (verbosity). Option: Kotlin, Scala, Lombok
//@Data
@Value
class Interval {
   /* private final */int start;
   /* private final */int end;

//    public Interval(int start, int end) {
//        this.start = start;
//        this.end = end;
//    }

    public boolean intervalsIntersect(Interval other) {
        // http://world.std.com/~swmcd/steven/tech/interval.html
        return start <= other.end && other.start <= end;
    }

//    public int getStart() {
//        return start;
//    }
//
//    public int getEnd() {
//        return end;
//    }
//
//    @Override
//    public String toString() {
//        return "Interval{" +
//            "start=" + start +
//            ", end=" + end +
//            '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Interval interval = (Interval) o;
//        return start == interval.start &&
//            end == interval.end;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(start, end);
//    }
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

    public String getMake() {
        return make;
    }

    public Interval getYearInterval() {
        return new Interval(startYear,endYear);
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

    public Interval getYearInterval() {
        return new Interval(startYear, endYear);
    }
}