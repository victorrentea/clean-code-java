package victor.training.cleancode;

import java.util.List;
import java.util.stream.Collectors;

class CarSearch {

  // run tests
  public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> carModels) {
//    Year year = Year.of(2024)
    List<CarModel> results = carModels.stream()
        .filter(carModel -> criteria.yearInterval().doesIntersect(carModel.yearInterval()))
        .collect(Collectors.toList());
    System.out.println("More filtering logic ...");
    return results;
  }

}

// any name you invent, look for 5 alternatives. brcause devs suck at names.
//class YearRange {
//class ManufacturerYearRange {
//class IntervalWindow {
//class YearsWindow {
//class IntWindow {
//class Range {

// every little class (not a Mongo Document) should be IMMUTABLE.
// why?
// - thread safety - do I do heavy multi threaded flows in microservices ?
// - code safety: to call a method without worrying of it changing the state of my parameter
// @Value (lombok)
// record Money(amount, currency) {}
record Interval(int start, int end) {
  Interval {
    if (start > end) { // self-validating constructor - scary for most developers. wild: < 5%
      throw new IllegalArgumentException("start larger than end");
    }
  }

  //  public static boolean intervalsIntersect(int start1, int end1, int start2, int end2) {
  public boolean doesIntersect(Interval interval2) {
    return start <= interval2.end && interval2.start <= end;
  }

  // uniform interface principle: you should not be able to tell the difference
  // between a getter and a method returning a "derived value" from the objects fields
  public int length() {
    return end - start;
  }
}


class MathUtil {
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

  public Interval yearInterval() {
    return new Interval(startYear, endYear);
  }
}

//@Document
class CarModel { // the Entity Model👑 test
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

  public Interval yearInterval() {
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
