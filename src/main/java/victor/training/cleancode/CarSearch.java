package victor.training.cleancode;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.util.List;
import java.util.stream.Collectors;

class CarSearch {

  // run tests
  public List<CarModel> filterCarModels(
      CarSearchCriteria criteria,
      List<CarModel> carModels) {

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

class TimeUtils {

}

//class Period,Interval,Range
// Value Object = Immutable, lacks identity (no PK)
// eg> Money{amount, currency}
@Embeddable
record Interval(int start, int end) {
  public Interval {
    if (start > end) { // scary! but useful in complex domains
      throw new IllegalArgumentException("start larger than end");
    }
  }

//  @AssertTrue // doesn't do anything by itself. until someone validates this instance (eg. Hibernate, @Validated)
//  public boolean isValid() {
//    return start <= end;
//  }

  public boolean intersects(Interval interval2) {
    return start <= interval2.end && interval2.start <= end;
  }
}


class CarSearchCriteria { // a DTO received from JSON
  private final int startYear;
  private final int endYear;
  //  private final Interval yearInterval;
  private final String make;

  public CarSearchCriteria(int startYear, int endYear, String make) {
    this.make = make;
    if (startYear > endYear) throw new IllegalArgumentException("start larger than end");
    this.startYear = startYear;
    this.endYear = endYear;
  }

  //  @JsonIgnore
  public Interval getYearInterval() { // helper methods in the DTO
    return new Interval(getStartYear(), getEndYear());
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
class CarModel/* implements Period*/ { // the Entity Model👑
  // @Id
  private Long id;
  private String make;
  private String model;
  //  private int startYear;
//  private int endYear;
  @Embedded // the 2 fields are persisted in the same CAR_MODEL table.
  private Interval yearInterval; //+semantic rich, +less fields

  protected CarModel() {
  } // for Hibernate

  public CarModel(String make, String model, int startYear, int endYear) {
    this.make = make;
    this.model = model;
//    if (startYear > endYear) throw new IllegalArgumentException("start larger than end");
//    this.startYear = startYear;
//    this.endYear = endYear;
    this.yearInterval = new Interval(startYear, endYear);
  }

  Interval getYearInterval() {
    return yearInterval;
  }

  // polluting the Domain Model with API structures
//  public boolean intersectYears(CarSearchCriteria criteria) {
//    return MathUtil.intervalsIntersect(
//        criteria.getStartYear(), criteria.getEndYear(),
//        getStartYear(), getEndYear());
//  }

  public Long getId() {
    return id;
  }

  public int getEndYear() {
    return yearInterval.end();
  }

  public int getStartYear() {
    return yearInterval.start();
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