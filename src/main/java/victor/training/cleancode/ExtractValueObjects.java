package victor.training.cleancode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

class ExtractValueObjects {

    // see tests
    public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {
        List<CarModel> results = models.stream()
                .filter(model -> criteria.getYearInterval().intersectsWith(model.getYearInterval()))
                .collect(Collectors.toList());
        System.out.println("More filtering logic");
        return results;
    }

    private void applyCapacityFilter() {
        System.out.println(new Interval(1000, 1600).intersectsWith(new Interval(1250, 2000)));
    }

}

class Alta {
    private void applyCapacityFilter() {
        System.out.println(new Interval(1000, 1600).intersectsWith(new Interval(1250, 2000)));
    }

}

class MathUtil {

}

record Interval(int start, int end) {
    public Interval { // risky, few teams are that bold/insane?
        if (start > end) throw new IllegalArgumentException("start larger than end");
    }

    public boolean intersectsWith(Interval other) {
        return start() <= other.end() && other.start() <= end();
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

    // enrich the data structures that sit there doing nothing around you, with bit of useful logic
    // to strip the complexity out of your core business logic
    Interval getYearInterval() {
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
class CarModel { // the holy Entity Model, your domain model object.
    // the most sacred object in your code base.
    // the object involved in the most complex business logic.
    @Id
    private Long id;
    private String make;
    private String model;
    //    private int startYear;
    //    private int endYear;
    private Interval yearInterval;

    protected CarModel() {
    } // for Hibernate

    public CarModel(String make, String model, int startYear, int endYear) {
        this.make = make;
        this.model = model;
        this.yearInterval = new Interval(startYear, endYear);
    }

    Interval getYearInterval() {
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
        dto.startYear = carModel.getYearInterval().start();
        dto.endYear = carModel.getYearInterval().end();
        dto.endYear = carModel.getYearInterval().end();
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