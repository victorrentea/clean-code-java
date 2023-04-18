package victor.training.cleancode;


import org.jetbrains.annotations.NotNull;

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

    private void applyCapacityFilter() {
        // BAD OLD
        System.out.println(new Interval(1000, 1600).intersects(new Interval(1250, 2000)));
    }

}

class Alta {
    private void applyCapacityFilter() {
        // BAD OLD
        System.out.println(new Interval(1000, 1600).intersects(new Interval(1250, 2000)));
    }

}

class IntervalUtil { // code smell: Util taking objects of yor own DOMAIN (that you control) = not DTOs

}

//record Interval(int start, int end) {} .// java 17
@Embeddable
class Interval { // = Value Object  (TM) = Immutable Small Object with no Identity
    private int start;
    private int end; // "effectively final" object -> no setters

    protected Interval() {
    } // for Hibernate

    public Interval(int start, int end) {
        if (start > end) throw new IllegalArgumentException("start larger than end");

        this.start = start;
        this.end = end;
    }

    public boolean intersects(Interval other) { // behavior next to state = REAL OOP
        return start <= other.end && other.start <= end;
    }

    public int getEnd() {
        return end;
    }
    public int getStart() {
        return start;
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

    Interval getYearInterval() {
        return new Interval(getStartYear(), getEndYear());
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
    //    private int startYear; // -2
    //    private int endYear;
    @Embedded
    private Interval yearInterval; // +1

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

    public Long getId() {
        return id;
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
        dto.startYear = carModel.getStartYear();
        dto.endYear = carModel.getEndYear();
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