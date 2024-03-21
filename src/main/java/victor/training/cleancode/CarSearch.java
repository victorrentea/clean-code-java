package victor.training.cleancode;

import java.util.List;
import java.util.stream.Collectors;

class CarSearch {

    // see tests
    public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> carModels) {
        List<CarModel> results = carModels.stream()
                .filter(carModel -> yearsIntersect(criteria, carModel))
                .collect(Collectors.toList());
        System.out.println("More filtering logic ...");
        return results;
    }

    private boolean yearsIntersect(CarSearchCriteria criteria, CarModel carModel) {
        return MathUtil.intersect(new Interval(criteria.getStartYear(), criteria.getEndYear()), new Interval(carModel.getStartYear(), carModel.getEndYear()));
    }

    private void applyCapacityFilter() {
        System.out.println(MathUtil.intersect(new Interval(1000, 1600), new Interval(1250, 2000)));
    }

}

class Alta {
    private void applyCapacityFilter() {
        System.out.println(MathUtil.intersect(new Interval(1000, 1600), new Interval(1250, 2000)));
    }

}

class MathUtil {

    public static boolean intersect(Interval interval1, Interval interval2) {
        return interval1.start() <= interval2.end() && interval2.start() <= interval1.end();
    }
}
//I was missing a concept in my code
record Interval(int start, int end) {}


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