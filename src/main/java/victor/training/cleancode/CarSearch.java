package victor.training.cleancode;

import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

class CarSearch {
  // TODO nu-i mai bine un WHERE decat 1k in RAM ?

  // run tests
  public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> carModels) {
    List<CarModel> results = carModels.stream()
        .filter(carModel -> MathUtil.intervalsIntersect(
            new Interval(criteria.getStartYear(), criteria.getEndYear()),
            new Interval(carModel.getStartYear(), carModel.getEndYear())))
        .collect(Collectors.toList());
    System.out.println("More filtering logic ...");
    return results;
  }
}

class SomeOtherClientCode {
  private void applyLengthFilter() { // pretend
    System.out.println(MathUtil.intervalsIntersect(new Interval(1000, 1600), new Interval(1250, 2000)));
  }
  private void applyCapacityFilter() { // pretend
    System.out.println(MathUtil.intervalsIntersect(new Interval(1000, 1600), new Interval(1250, 2000)));
  }
}

class MathUtil {

  public static boolean intervalsIntersect(Interval interval1, Interval interval2) {
    return interval1.getStart() <= interval2.getEnd() && interval2.getStart() <= interval1.getEnd();
  }

  // veche naspa pe care vreau sa o omor
}

// value object (design pattern) =
// = immutable object that has no identity (no ID field)., eg Money{amount, currency}
@Value // = un fel de @Data da mai bun: toate campurile sunt private final
final class Interval {
  int start;
  int end;
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