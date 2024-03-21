package victor.training.cleancode;

import java.util.List;
import java.util.stream.Collectors;

class CarSearch {

    // see tests
    public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> carModels) {
        List<CarModel> results = carModels.stream()
                .filter(carModel -> criteria.yearInterval().intersects(carModel.yearInterval()))
                .collect(Collectors.toList());
        System.out.println("More filtering logic ...");
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
//I was missing a concept in my code
record Interval(int start, int end) {
    public boolean intersects(Interval other) {
        return start <= other.end && other.start <= end;
    }
}


class CarSearchCriteria { // smells like JSON ...
    private final int startYear;
//    @Schema(description = "The end year")
    private final int endYear;
//    private final Interval yearInterval; // breaks contract. DON;T
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

    // my app logic uses this method
//    @JsonIgnore
    Interval yearInterval() {
        return new Interval(startYear, endYear);
    }
}

// @Entity @Document
// Holy Domain Model - the team has full exclusive control on it
class CarModel { // the holy Entity Model
    // @Id
    private Long id;
    private String make;
    private String model;
//    private int startYear;
//    private int endYear;
    // @Embedded // stored in the same CAR_MODEL table > no schema change thanks to ORM
    private Interval yearInterval;

    protected CarModel() {
    } // for Hibernate

    public CarModel(String make, String model, int startYear, int endYear) {
        this.make = make;
        this.model = model;
        if (startYear > endYear) throw new IllegalArgumentException("start larger than end");
        this.yearInterval = new Interval(startYear, endYear);
    }

    public Long getId() {
        return id;
    }

    public int getEndYear() {
        return yearInterval.end();
    }

    public int getStartYear() {
        return yearInterval.start();
    }

    public Interval yearInterval() {
        return yearInterval;
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