package victor.training.cleancode;

import java.util.List;
import java.util.stream.Collectors;

class ExtractValueObjects {

    // see tests
    public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {
        System.out.println("More filtering logic");

        return models.stream()
                .filter(model -> criteria.getYearInterval().intersects(model.getYearInterval()))
                .collect(Collectors.toList());
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


class CarSearchCriteria {
    private final Interval yearInterval;
    private final String make;

    public CarSearchCriteria(int startYear, int endYear, String make) {
        this.make = make;
        yearInterval = new Interval(startYear, endYear);
    }

    public Interval getYearInterval() {
        return yearInterval;
    }

    public int getStartYear() {
        return yearInterval.getStart();
    }

    public int getEndYear() {
        return yearInterval.getEnd();
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
    private Interval yearInterval;

    private CarModel() {
    } // for Hibernate

    public CarModel(String make, String model, int startYear, int endYear) {
        this.make = make;
        this.model = model;
        yearInterval = new Interval(startYear, endYear);
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

    public Interval getYearInterval() {
        return yearInterval;
    }
}


class CarModelMapper {
    public CarModelDto toDto(CarModel carModel) {
        CarModelDto dto = new CarModelDto();
        dto.make = carModel.getMake();
        dto.model = carModel.getModel();
        dto.startYear = carModel.getYearInterval().getStart();
//        dto.endYear = carModel.getEndYear(); // PROST pt ca incarca Entity cu o metoda care nu zice nimic inteligent. "Middle Man" code smell.
        dto.endYear = carModel.getYearInterval().getEnd();
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