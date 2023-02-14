package victor.training.cleancode;


import lombok.Data;
import lombok.EqualsAndHashCode.Exclude;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
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

// effectively immutable value object
@Embeddable
class Interval {
    private /*final*/ int start;
    private /*final*/ int end;

    protected Interval() {} // just for Hibenate to hidrate the obj when loading from DB

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
@Getter
        //@Data // DANGEROUS on @ENtity: because
        // 1 hashCode/equals will return a different value when Hib assings the id at repo.save()
        // DO NOT implem hash/eq on @Entity
        // 2 generated toString includes even children collections -~~~~> Lazy Loading  SQL running because of a tostring
        // 3 setters for free
class CarModel {
    @Id
    @Exclude
    private Long id;
    private String make;
    private String model;
    @Embedded
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
//        if (dto.startYear > dto.endYear) throw new IllegalArgumentException("start larger than end");
        Interval yearInterval = new Interval(dto.startYear, dto.endYear);
        return new CarModel(dto.make, dto.model, yearInterval);
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
//    private MySmartXmlParser b;
//    //    @Autowired
//    //    private BFactory bf;
//
//    public void method() {
//        //       bf.getB(1).stuff(-1);
//        getB(1).stuff(-1);
//    }
//    public MySmartXmlParser getB(int param) { // in testing this requires partial Mocks (@Spy) <- some ppl are very against these
//        return new MySmartXmlParser(param); // in tests -> mocks returning mocks
//    }
//}
////@Service
////class BFactory {
////    public MySmartXmlParser getB(int param) { // in testing this requires partial Mocks (@Spy) <- some ppl are very against these
////        return new MySmartXmlParser(param); // in tests -> mocks returning mocks
////    }
////
////}
//
//// XML parse
//class MySmartXmlParser {
//    private final int param; // state <  cached some stuff
//
//    public MySmartXmlParser(int param) {
//        this.param = param;
//    }
//
//    public void stuff(int arg) {
//
//    }
//}