package victor.training.cleancode;

import javax.persistence.Embedded;
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
//    private final Interval yearInterval; // nice idea, but I don't want to break my API

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

//@Entity
class CarModel {
    //   @Id
    private Long id;
    private String make;
    private String model;
    @Embedded
    private Interval yearInterval;

    private CarModel() {
    } // for Hibernate

    public CarModel(String make, String model, Interval yearInterval) { // for developers
        this.make = make;
        this.model = model;
        this.yearInterval = yearInterval;
    }

    public Long getId() {
        return id;
    }

    public Interval getYearInterval() {
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
        dto.startYear = carModel.getYearInterval().getStart();

        dto.endYear = carModel.getYearInterval().getEnd();//replace this
        dto.endYear = carModel.getYearInterval().getEnd();// with this, everywhere!!

        dto.endYear = carModel.getYearInterval().getEnd();
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