package victor.training.cleancode;

import java.util.List;
import java.util.stream.Collectors;

class CarSearch {

  // run tests
  public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> carModels) {
    List<CarModel> results = carModels.stream()
        .filter(carModel -> criteria.getYearRange().intersects(carModel.getYearRange()))
        .collect(Collectors.toList());
    System.out.println("More filtering logic ...");
    return results;
  }
}

class SomeOtherClientCode {
  private void applyLengthFilter() { // pretend
    System.out.println(new Range(1000, 1600).intersects(new Range(1250, 2000)));
  }
  private void applyCapacityFilter() { // pretend
    Range range1 = new Range(1000, 1600);
    System.out.println(range1.intersects(new Range(1250, 2000)));
  }
}

class MathUtil {
}

record Range(int start, int end) {
  public boolean intersects(Range other) {
    return start <= other.end && other.start <= end;
  }
}


class CarSearchCriteria { // a DTO received from JSON (payload of a request)
  // don't change its fields -> breaking change your API clients
  private final int startYear;
  private final int endYear;
  private final String make;

  public CarSearchCriteria(int startYear, int endYear, String make) {
    this.make = make;
    if (startYear > endYear) throw new IllegalArgumentException("start larger than end");
    this.startYear = startYear;
    this.endYear = endYear;
  }

  Range getYearRange() { //helper function to support my core logic , good practice
    return new Range(startYear, endYear);
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
class CarModel { // the Entity Model👑 < best part is that we should continuously improve its modelling
  // @Id
  private Long id;
  private String make;
  private String model;
  private Range yearRange; // dramatic change : from 2 fields to a Range object

  protected CarModel() {
  } // for Hibernate

  public CarModel(String make, String model, int startYear, int endYear) {
    this.make = make;
    this.model = model;
    if (startYear > endYear) throw new IllegalArgumentException("start larger than end");
    this.yearRange = new Range(startYear, endYear);
  }

  Range getYearRange() {
    return yearRange;
  }

  public Long getId() {
    return id;
  }

  public int getEndYear() {
    return yearRange.end();
  }

  public int getStartYear() {
    return yearRange.start();
  }

  public String getMake() {
    return make;
  }

  public String getModel() {
    return model;
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