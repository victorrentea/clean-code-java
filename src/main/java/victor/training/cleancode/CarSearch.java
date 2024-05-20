package victor.training.cleancode;

import java.util.List;

class CarSearch {

  // run tests
  public List<CarModel> filterCarModels(CarSearchCriteria criteria,
                                        List<CarModel> carModels) { // SELECT * FROM CAR_MODEL; gresit daca aduce > 100-1000 randuri
    List<CarModel> results = carModels.stream() // variabila temporara
        .filter(carModel -> MathUtil.intervalsIntersect( // lambda prea lung
            criteria.getStartYear(), criteria.getEndYear(),
            carModel.getStartYear(), carModel.getEndYear())) // prea multi parametri
//        .collect(Collectors.toList()); // mutabil, nu e ok
        .toList();// imutabil
         // mutabil, nu e ok
//    log.trace("Found cars {}", results); // sa pui log in loc de breakpoint permite si
    // sarmanului suflet (poate tot tu) care vine dupa sa repeare si el pe a-ci ceva.
    // NU VA mai trebui sa intre in breakpoint ci doar pune log.level=trace
    return results;
  }

}

class SomeOtherClientCode {
  private void applyLengthFilter() { // pretend
    System.out.println(MathUtil.intervalsIntersect(1000, 1600, 1250, 2000));
  }
  private void applyCapacityFilter() { // pretend
    System.out.println(MathUtil.intervalsIntersect(1000, 1600, 1250, 2000));
  }
}

class MathUtil {

  // maine vreau asta (BUN)
  public static boolean intervalsIntersect(Interval interval1, Interval interval2) {
    return interval1.start() <= interval2.end() && interval2.start() <= interval1.end();
  }
  @Deprecated
  /**
   * @deprecated Use {@link #intervalsIntersect(Interval, Interval)} instead
   */
  //acum am asta (RAU):
  public static boolean intervalsIntersect(int start1, int end1, int start2, int end2) {
    return start1 <= end2 && start2 <= end1;
  }
}
record Interval(int start, int end) {}



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