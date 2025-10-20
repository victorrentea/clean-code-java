package victor.training.cleancode;

import java.util.List;
import java.util.stream.Collectors;

class CarSearch {

  // run tests
  public List<CarModel> filterCarModels(
      CarSearchCriteria criteria, List<CarModel> carModels) {
    List<CarModel> results = carModels.stream()
        .filter(carModel -> doYearsIntersect(criteria, carModel))
        .collect(Collectors.toList());
    System.out.println("More filtering logic ...");
    return results;
  }

  private boolean doYearsIntersect(CarSearchCriteria criteria, CarModel carModel) {
    int start1 = criteria.getStartYear();
    int end1 = criteria.getEndYear();
    int start2 = carModel.getStartYear();
    int end2 = carModel.getEndYear();
    return MathUtil.intervalsIntersect(
        new Interval(start1, end1),
        new Interval(start2, end2)
    );
  }
}

class SomeOtherClientCode {
  private void applyLengthFilter() { // pretend
    System.out.println(MathUtil.intervalsIntersect(
        new Interval(1000, 1600),
        new Interval(1250, 2000)
    ));
  }
  private void applyCapacityFilter() { // pretend
    System.out.println(MathUtil.intervalsIntersect(
        new Interval(1000, 1600),
        new Interval(1250, 2000)
    ));
  }
}

//record IntervalDates() {}
//record Interval(int) {}
//record 2PerechiDeCapete(int) {}
//record OPerecheDeCapete(int,int) {}
//record PeriodDeIncadrare(int,int) {}
//record DOuaINtervale(int) {}
//record PatruAni(int,int,int,int) {}
//record Period(int startYear,int endYear) {}
record Interval(int startYear, int endYear) {}
class MathUtil {
  public static boolean intervalsIntersect(
      Interval interval1, Interval interval2) {
    return interval1.startYear() <= interval2.endYear()
           && interval2.startYear() <= interval1.endYear();
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
