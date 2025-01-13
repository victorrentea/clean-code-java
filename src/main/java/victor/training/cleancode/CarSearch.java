package victor.training.cleancode;

import java.util.List;
import java.util.stream.Collectors;

class CarSearch {

  // run tests
  public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> carModels) {
    List<CarModel> results = carModels.stream()
        .filter(carModel -> matchesProductionYears(criteria, carModel))
        .collect(Collectors.toList());
    System.out.println("More filtering logic ...");
    return results;
  }

  private boolean matchesProductionYears(CarSearchCriteria criteria, CarModel carModel) {
    Interval criteriaInterval = new Interval(criteria.getStartYear(), criteria.getEndYear());
    Interval carInterval = new Interval(carModel.getStartYear(), carModel.getEndYear());
    return MathUtil.intervalsIntersectNew(criteriaInterval, carInterval);
  }
}

class SomeOtherClientCode {
  private void applyLengthFilter() { // pretend
    System.out.println(MathUtil.intervalsIntersectNew(new Interval(1000, 1600), new Interval(1250, 2000)));
  }
  private void applyCapacityFilter() { // pretend

    System.out.println(MathUtil.intervalsIntersectNew(new Interval(1000, 1600), new Interval(1250, 2000)));
  }
}

class MathUtil {
  //  public static boolean intervalsIntersect(IntervalIntersectsParams ) {... prea specific ACESTEI fucnctii, non-reusable
  public static boolean intervalsIntersectNew(Interval interval1, Interval interval2) { // BUNA
    return interval1.start() <= interval2.end() && interval2.start() <= interval1.end();
  }
}

record Interval(int start, int end) { // campurile sunt imutabile + sintaxa compacta
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
class CarModel { // the Entity Model👑
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