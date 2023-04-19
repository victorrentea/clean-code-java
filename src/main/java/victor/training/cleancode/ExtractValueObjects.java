package victor.training.cleancode;


import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.stream.Collectors;

class ExtractValueObjects {

    // see tests
    public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {
        List<CarModel> results = models.stream()
                .filter(model -> criteria.getYearInterval().intersects(model.getYearInterval()))
                .collect(Collectors.toList());
        System.out.println("More filtering logic");
        return results;
    }


    //    private static boolean intersectYears(CarSearchCriteria criteria, CarModel model) {
    //        return MathUtil.intersects(
    //                criteria.getStartYear(), criteria.getEndYear(),
    //                model.getStartYear(), model.getEndYear());
    //    }

    private void applyCapacityFilter() {
        System.out.println(new Interval(1000, 1600).intersects(new Interval(1250, 2000)));
    }
}

class Alta {
    private void applyCapacityFilter() {
        System.out.println(new Interval(1000, 1600).intersects(new Interval(1250, 2000)));
    }
}

class MathUtil {
    // it makes the Util class not generic anymore. It's not a util!
    //    public static boolean intersects(CarSearchCriteria criteria, CarModel model) {
    //        return intersects(
    //                criteria.getStartYear(), criteria.getEndYear(),
    //                model.getStartYear(), model.getEndYear());
    //    }

    // OLD BAD
}

@Embeddable
class Interval { // effectively final object (no setters)
    private int start;
    private int end;

    protected Interval() {
    } // for Hibernate

    Interval(int start, int end) {
        if (start > end) throw new IllegalArgumentException("start larger than end");

        this.start = start;
        this.end = end;
    }

    // NEW BETTER
    public boolean intersects(Interval interval2) {
        return start <= interval2.end && interval2.start <= end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}

//class CriteriaHelper  { // we can do simpler. why a new class. Why not the method inside the criteria?
//    private final CarSearchCriteria criteria;
//
//    CriteriaHelper(CarSearchCriteria criteria) {
//        this.criteria = criteria;
//    }
//
//    public boolean matchesIntersect(int start, int end) {
//        return MathUtil.intersects(
//                criteria.getStartYear(), criteria.getEndYear(),
//                start, end);
//    }
//}

class CarSearchCriteria { // smells like JSON ...
    private final int startYear;
    private final int endYear;
    private final String make;

    public CarSearchCriteria(int startYear, int endYear, String make) {
        this.make = make;
        if (startYear > endYear) throw new IllegalArgumentException("start larger than end");
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public Interval getYearInterval() {
        return new Interval(startYear, endYear);
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

@Entity
class CarModel { // the holy Entity Model
    // Domain Model holding business data. one of the most sacred classes in the app
    @Id
    private Long id;
    private String make;
    private String model;
    @Embedded // the best feature of Hib when it comes to making @Entities simple
    private Interval yearInterval;
    //    private int startYear;
    //    private int endYear;

    protected CarModel() {
    } // for Hibernate

    public CarModel(String make, String model, int startYear, int endYear) {
        this.make = make;
        this.model = model;
        yearInterval = new Interval(startYear, endYear);
    }

    public Interval getYearInterval() {
        return yearInterval;
    }

    //    public boolean matches(CarSearchCriteria criteria) {
    //        return MathUtil.intersects(
    //                criteria.getStartYear(), criteria.getEndYear(),
    //                startYear, endYear);
    //    }
    public Long getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }


    @Override
    public String toString() {
        return "CarModel{" +
               "make='" + make + '\'' +
               ", model='" + model + '\'' +
               '}';
    }
}


class CarModelMapper {
    public CarModelDto toDto(CarModel carModel) {
        CarModelDto dto = new CarModelDto();
        dto.make = carModel.getMake();
        dto.model = carModel.getModel();
        dto.startYear = carModel.getYearInterval().getStart();
        dto.startYear = carModel.getYearInterval().getStart();
        dto.endYear = carModel.getYearInterval().getEnd();
        return dto;
    }

    public CarModel fromDto(CarModelDto dto) {
        return new CarModel(dto.make, dto.model, dto.startYear, dto.endYear);
    }
}

class CarModelDto {
    public String make;
    public String model;
    public int startYear;
    public int endYear;
}