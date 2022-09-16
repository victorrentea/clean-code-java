package victor.training.cleancode;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

class ExtractValueObjects {

    // see tests
    public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {
        Interval criteriaInterval = new Interval(criteria.getStartYear(), criteria.getEndYear());
        List<CarModel> results = models.stream()
                .filter(model -> criteriaInterval.intersects(model.getYearInterval()))
                .collect(toList());

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

class MathUtil {

}




class CarSearchCriteria { // JSON
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

//@Entity
class CarModel {
    //   @Id
    private Long id;
    private String make;
    private String model;
//    private int startYear;
//    private int endYear;
//    @Embedded
    private Interval yearInterval; // = -1 field in my entity

    protected CarModel() {
    } // for Hibernate

    public CarModel(String make, String model, Interval yearInterval) { // for developers to use
        this.make = Objects.requireNonNull(make);
        this.model = Objects.requireNonNull(model);
        this.yearInterval = Objects.requireNonNull(yearInterval);
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
        dto.startYear = carModel.getYearInterval().getStart();
        dto.startYear = carModel.getYearInterval().getStart();
        dto.startYear = carModel.getYearInterval().getStart();
        dto.startYear = carModel.getYearInterval().getStart();
        dto.startYear = carModel.getYearInterval().getStart();
        dto.startYear = carModel.getYearInterval().getStart();
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