package victor.training.cleancode;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.stream.Collectors;

class ExtractValueObjects {

    // see tests
    public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {
        Interval yearInterval = new Interval(criteria.getStartYear(), criteria.getEndYear());
        List<CarModel> results = models.stream()
//                .filter(model -> model.matchesYears(criteria)) // violates arch rules: couples Domain Model👑 to the API
                .filter(model -> yearInterval.intersects(model.getYearInterval()))
                .collect(Collectors.toList());
        System.out.println("More filtering logic");
        return results;
    }

    private void applyCapacityFilter() {
        System.out.println(new Interval(1000, 1600).intersects(new Interval(1250, 2000)));
    }

}

class Alta {
    private void applyCapacityFilter() {
        System.out.println(new Interval(1000, 1600).intersects(new Interval(1250, 2000)));
    }
}

//class MathUtil {
//    // cofession
////    public static void method(DomainObject myObject) {
////        // 3-5 lines of domain logic that would help me in many places => MOVE INSIDE the Domain Object
////    }
//}
//we identified a new Value Object
//    - (usually small) group of fields
//    - immutable
//    - it does not have persistent ID (PK) - just a "value"
//    - hashcode/equals involves all fields
//@Embeddable
class Interval {
    private final int start;
    private final int end;

    Interval(int start, int end) {
        // #kudos for this:
        if (start > end) throw new IllegalArgumentException("start larger than end");
        // model becomes more strict with conssitency (but sometimes makes this class less reusable)

        this.start = start;
        this.end = end;
    }

    public boolean intersects(Interval other) { // OOP teacher= proud
        return start <= other.end && other.start <= end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}

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
class CarModel {
    @Id
    private Long id;
    private String make;
    private String model;
    private Interval yearInterval;

    protected CarModel() {
    } // for Hibernate

    public CarModel(String make, String model, Interval yearInterval) {
        this.make = make;
        this.model = model;
        this.yearInterval = yearInterval;
    }

    public Interval getYearInterval() {
        return yearInterval;
    }

    public Long getId() {
        return id;
    }

    // code smell : Middle Man : stupid method
    //    public int getEndYear() {
    //        return yearInterval.getEnd();
    //    }

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
        dto.endYear = carModel.getYearInterval().getEnd();
        dto.endYear = carModel.getYearInterval().getEnd();
        dto.endYear = carModel.getYearInterval().getEnd();
        dto.endYear = carModel.getYearInterval().getEnd();
        dto.endYear = carModel.getYearInterval().getEnd();
        dto.endYear = carModel.getYearInterval().getEnd();
        return dto;
    }

    public CarModel fromDto(CarModelDto dto) {
        return new CarModel(dto.make, dto.model, new Interval(dto.startYear, dto.endYear));
    }
}

class CarModelDto {
    public String make;
    public String model;
    public int startYear;
    public int endYear;
}


//@Service
//class ServiceA {
//    @Autowired
//    private ServiceB b;
//
//    public void method() {
//       getB(1);
//    }
//
//    public void getB(int param) {
//        b.stuff(param);
//    }
//}
//
//@Service
//class ServiceB {
//
//    public void stuff(int param) {
//
//    }
//}