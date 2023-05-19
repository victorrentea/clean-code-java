package victor.training.cleancode;

import lombok.With;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//        carModelRepo.findAll().stream().filter() => Pro: tii filtrarea in cod java nu jpql/sql
//        SELECT WHERE (@Query dedicate) => Pro: PERFORMANTA CPU+MEM+RETEA; reuse @Query
class ExtractValueObjects {

    // see tests
    public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {
        List<CarModel> results = models.stream()
                .filter(model -> matchesYears(criteria, model))
                .collect(Collectors.toList());
        System.out.println("More filtering logic");
//        criteria.getYearInterval().
        return results;
    }

    private static boolean matchesYears(CarSearchCriteria criteria, CarModel model) {
        Interval criteriaInterval = criteria.getYearInterval();
        Interval modelInterval = model.getYearInterval();
        return criteriaInterval.intersects(modelInterval);
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

class MathUtil {
//    public static boolean intersects(List<Interval> intervale) { // OVERENGINEERING ca poate maine e mai reusable

}
// DTO = Data Transfer Object = cara date peste retea (JSON)
// POJO = camp private + getter setter @Data
// VO = Value Object = immutabil❤️❤️❤️ @Value
//class Wrapper3
//class TwoIntervals {
//@Data // < NU!
//@Value // DA!mmutabil❤️❤️❤️ @Value = @Data + campuri private final (fara setter
@Embeddable
class Interval {
    private int start;
    private int end;
    protected Interval() {} // for Hibernate only

    public Interval(int start, int end) {
        if (start > end) throw new IllegalArgumentException("start larger than end");
        this.start = start;
        this.end = end;
    }
    public int getStart() {
        return start;
    }
    public int getEnd() {
        return end;
    }
    // noua, buna
    // veche, naspa
    public boolean intersects(Interval other) {
        return start <= other.end && other.start <= end;
    }
}

class CarSearchCriteria { // smells like JSON ...
    private final int startYear;
    private final int endYear;
//    private final Interval yearInterval;
    private final String make;

    public CarSearchCriteria(int startYear, int endYear, String make) {
        this.make = make;
        if (startYear > endYear) throw new IllegalArgumentException("start larger than end");
        this.startYear = startYear;
        this.endYear = endYear;
    }

    @JsonIgnore
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
    @Id
    private Long id;
    private String make;
    private String model;
    @Embedded
    private Interval yearInterval;

//    @ElementCollection
//    private List<String> phones = new ArrayList<>();

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