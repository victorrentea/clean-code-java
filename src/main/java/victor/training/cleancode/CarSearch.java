package victor.training.cleancode;

import java.util.List;
import java.util.stream.Collectors;

class CarSearch {

  // run tests
  public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> carModels) {
    List<CarModel> results = carModels.stream()
        .filter(carModel -> criteria.getYearInterval().intersects(carModel.getYearInterval()))
        .collect(Collectors.toList());
    System.out.println("More filtering logic ...");
    return results;
  }

}

class SomeOtherClientCode {
  private void applyLengthFilter() { // pretend
    System.out.println(new Interval(1000, 1600).intersects(new Interval(1250, 2000)));
  }
  private void applyCapacityFilter() { // pretend
    System.out.println(new Interval(1000, 1600).intersects(new Interval(1250, 2000)));
  }
}

// Value Object = Immutable and lacking identity
// canonical example = Money{amount, currency}
// the more value objects, the better
/** Closed interval */
record Interval(int start, int end) {
  /** comutative */
  public boolean intersects(Interval other) {
    return start <= other.end && other.start <= end;
  }
}


class CarSearchCriteria { // a DTO received from JSON
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

// @Entity
class CarModel { // the Entity Model👑, private to my app. It is an arch goal to keep it private to my logic
  // DDD-style
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

  Interval getYearInterval() {
    return new Interval(startYear, endYear);
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