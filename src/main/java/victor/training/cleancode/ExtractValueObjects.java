package victor.training.cleancode;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.stream.Collectors;

class ExtractValueObjects {

  // see tests
  public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> carModels) {
    List<CarModel> results = carModels.stream()
        .filter(carModel -> criteria.getInterval().intersects(carModel.getInterval()))
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

record Interval(int start, int end) {
  public Interval {
    if (start > end) throw new IllegalArgumentException("start larger than end");

  }
  public boolean intersects(Interval other) {
    return start <= other.end && other.start <= end;
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

  public Interval getInterval() {
    return new Interval(startYear, endYear);
  }
}

@Entity
class CarModel { // the holy Entity Model
  @Id
  private Long id;
  private String make;
  private String model;
  private Interval yearInterval;

  public CarModel(String make, String model, Interval yearInterval) {
    this.make = make;
    this.model = model;
    this.yearInterval = yearInterval;
  }

  public Interval getInterval() {
    return yearInterval;
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
    dto.startYear = carModel.getYearInterval().start();
    dto.endYear = carModel.getYearInterval().end();
    dto.endYear = carModel.getYearInterval().end();
    dto.endYear = carModel.getYearInterval().end();
    dto.endYear = carModel.getYearInterval().end();
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