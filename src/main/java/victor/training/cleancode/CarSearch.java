package victor.training.cleancode;

import java.util.List;

class CarSearch {

    // see tests
    public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> carModels) {
        List<CarModel> results = carModels.stream()
                .filter(carModel -> matchesYears(criteria, carModel))
                .toList(); // hope the caller doesn't do .add .remove
        System.out.println("More filtering logic ...");
        return results;
    }

    private boolean matchesYears(CarSearchCriteria criteria, CarModel carModel) {
        // what if criteria gave me an interval ??
      return new Interval(criteria.startYear(), criteria.endYear())
//        criteria.yearInterval()
          .intersects(new Interval(carModel.startYear(), carModel.endYear()));
    }

    private void applyCapacityFilter() {
        System.out.println(new Interval(1000, 1600).intersects(new Interval(1250, 2000)));
        System.out.println(new Interval(1000, 1600).intersects(new Interval(1250, 2000)));
        System.out.println(new Interval(1000, 1600).intersects(new Interval(1250, 2000)));
        System.out.println(new Interval(1000, 1600).intersects(new Interval(1250, 2000)));
    }

}

class Alta {
    private void applyCapacityFilter() {
        System.out.println(new Interval(1000, 1600).intersects(new Interval(1250, 2000)));


//        new Interval(1, 3).intersects(new Interval(2, 4));
        // new causes performance?

    }

}
class MathUtil {
    // destroying this static method forces the caller to use my Interval
    // or, unaware of Interval, will write their own (buggy) implementation of intersects
}

// Value Object: grouping of data, immutable small
record Interval(int start, int end) {
    public boolean intersects(Interval other) {
        return end >= other.start && start <= other.end;
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

    public int startYear() {
        return startYear;
    }

    public int endYear() {
        return endYear;
    }

    public String getMake() {
        return make;
    }
}

// @Entity
class CarModel { // the holy Entity Model
    // @Id
    private Long id;
    private String make;
    private String model;
    private int startYear;
    private int endYear;

    protected CarModel() {
    } // for Hibernate

    public CarModel(String make, String model, int startYear, int endYear) {
        this.make = make;
        this.model = model;
        if (startYear > endYear) throw new IllegalArgumentException("start larger than end");
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public Long getId() {
        return id;
    }

    public int endYear() {
        return endYear;
    }

    public int startYear() {
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
        dto.startYear = carModel.startYear();
        dto.endYear = carModel.endYear();
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