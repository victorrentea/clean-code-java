package victor.training.cleancode;

import java.util.List;
import java.util.stream.Collectors;

class CarSearch {

  // run tests
  public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> carModels) {
    List<CarModel> results = carModels.stream()
        .filter(carModel -> intervalIntersect(criteria, carModel))
        .collect(Collectors.toList());
    System.out.println("More filtering logic ...");
    return results;
  }

  private boolean intervalIntersect(CarSearchCriteria criteria, CarModel carModel) {
    // - pass the criteria to new Interval(criteria)
    // - receive the two interval objects as params to this func. extract intervals from criteria/carModel in other places
    // - carModel+criteria.getYearInterval():Interval ?
//    return new Interval(criteria)
    return criteria.getYearInterval()
        .intersects(
            carModel.getYearInterval());
  }

  //  private Predicate<CarModel> yesICan_BUT_I_SHOULDNT(CarSearchCriteria criteria) {
//    // SCARY TO JAVA DEVS BECAUSE you don;t know
//    // when, how many times, in what transaction, in what thread, will the function you returned be used and actually called
//    return carModel -> MathUtil.intervalsIntersect(
//        criteria.getStartYear(), criteria.getEndYear(),
//        carModel.getStartYear(), carModel.getEndYear());
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
  //  public Interval(CarSearchCriteria criteria) {
//    this(criteria.getStartYear(), criteria.getEndYear());
//  }
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

  public int getStartYear() {
    return startYear;
  }

  public int getEndYear() {
    return endYear;
  }

  public String getMake() {
    return make;
  }

  public Interval getYearInterval() {
    return new Interval(startYear, endYear);
  }
}

// @Entity
class CarModel { // the Entity Model👑 test
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

  public Interval getYearInterval() {
    return new Interval(startYear, endYear);
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
