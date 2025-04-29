package victor.training.cleancode;

import java.util.List;
import java.util.stream.Collectors;

class CarSearch {

  // run tests
  public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> carModels) {
    List<CarModel> results = carModels.stream()
        .filter(carModel -> criteria.yearInterval().intersects(carModel.yearInterval()))
        .collect(Collectors.toList());
    System.out.println("More filtering logic ...");
    return results;
  }

  //  private Predicate<CarModel> yesICan_BUT_I_SHOULDNT(CarSearchCriteria criteria) {
//    // SCARY TO JAVA DEVS BECAUSE you don;t know
//    // when, how many times, in what transaction, in what thread, will the function you returned be used and actually called
//    return carModel -> MathUtil.intervalsIntersect(
//        criteria.startYear(), criteria.endYear(),
//        carModel.startYear(), carModel.endYear());
//  }
}

class SomeOtherClientCode {
  private void applyLengthFilter() { // pretend
    System.out.println(new Interval(1000, 1600).intersects(new Interval(1250, 2000)));
  }
  private void applyCapacityFilter() { // pretend
    System.out.println(new Interval(1000, 1600).intersects(new Interval(1250, 2000)));
  }
}

class MathUtil {

}

// RULE: any name you pick, first find 5 (FIVE) options: Period, Pair{left,right}, Range, Timespan
//@Value
// a value object = little immutable class lacking PK (vs an Entity)
record Interval(int start, int end) {
  Interval { // X-FEW teams do validation in constructors
    if (start > end) throw new IllegalArgumentException("start larger than end");
  }
  public boolean intersects(Interval other) {
    return start <= other.end && other.start <= end;
  }
}


class CarSearchCriteria { // a DTO received from JSON
  private final int startYear;
  private final int endYear;
  //  private final Interval yearInterval; // NO structure change in API MOdel
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

  public Interval yearInterval() {
    return new Interval(startYear, endYear);
  }
}

// @Entity
class CarModel { // the Entity Model👑 test
  // @Id
  private Long id;
  private String make;
  private String model;
  private Interval yearInterval;

  protected CarModel() {
  } // for Hibernate

  public CarModel(String make, String model, int startYear, int endYear) {
    this.make = make;
    this.model = model;
    this.yearInterval = new Interval(startYear, endYear);
  }

  public Interval yearInterval() {
    return yearInterval;
  }

  public Long id() {
    return id;
  }

  public int endYear() {
    return yearInterval.end();
  }

  public int startYear() {
    return yearInterval.start();
  }

  public String make() {
    return make;
  }

  public String model() {
    return model;
  }
}

class CarModelMapper {
  public CarModelDto toDto(CarModel carModel) {
    CarModelDto dto = new CarModelDto();
    dto.make = carModel.make();
    dto.model = carModel.model();
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
