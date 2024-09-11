package victor.training.cleancode;

import lombok.Value;

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
    System.out.println(MathUtil.intervalsIntersect(1000, 1600, 1250, 2000));
  }
  private void applyCapacityFilter() { // pretend
    System.out.println(MathUtil.intervalsIntersect(1000, 1600, 1250, 2000));
  }
}

class MathUtil {
  /**
   * @deprecated Use {@link Interval#intersects(Interval)} instead
   */
  @Deprecated
  public static boolean intervalsIntersect(int start1, int end1, int start2, int end2) {
    return new Interval(start1, end1).intersects(new Interval(start2, end2));
  }

}

// vreau sa fie global in aplicatie sau specific fluxului meu?
@Value
final class Interval {
  int start;
  int end;

  public Interval(int start, int end) {
    if (start > end) {  // TODO vrentea 11.09.2024:   de confirmat cu produsu, am trimis mai lui x@..com
      this.end = start;
      this.start = end;
    } else {
      this.start = start;
      this.end = end;
    }
  }

  boolean intersects(Interval other) {
    return start <= other.end && end >= other.start;
  }

}

//@Value// love mai mult decat @Data, pt ca e immutable= private tot si final -setteri si +constructor
//class Interval2 {
//  int start;
//  int end;
//}

class CarSearchCriteria { // a DTO received from JSON
  private final int startYear;
  private final int endYear;
  private final String make;

  public CarSearchCriteria(int startYear, int endYear, String make) {
    this.make = make;
    if (startYear > endYear) {
      throw new IllegalArgumentException("start larger than end");
    }
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
    if (startYear > endYear) {
      throw new IllegalArgumentException("start larger than end");
    }
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