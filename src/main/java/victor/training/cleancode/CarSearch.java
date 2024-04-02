package victor.training.cleancode;

import java.util.List;
import java.util.stream.Collectors;

class CarSearch {

  // run tests
  public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> carModels) {
    List<CarModel> results = carModels.stream()
        .filter(carModel -> criteria.yearInterval().intersectsWith(carModel.yearInterval()))
        .collect(Collectors.toList());
    System.out.println("More filtering logic ...");
    return results;
  }

}

// ACUM
// A) FP
// B) refactor codu vostru real


class SomeOtherClientCode {
  private void applyLengthFilter() { // pretend
    System.out.println(new Interval(1000, 1600).intersectsWith(new Interval(1250, 2000)));
  }

  private void applyCapacityFilter() { // pretend
    System.out.println(new Interval(1000, 1600).intersectsWith(new Interval(1250, 2000)));
  }
}

class MathUtil {

}
//@Embbedable
record Interval(/*@Min(0)*/ int start, int end) {
  Interval {
    if (start>end) throw new IllegalArgumentException();
  }
  public boolean intersectsWith(Interval interval) {
    return start <= interval.end && interval.start <= end;
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

  Interval yearInterval() {
    return new Interval(getStartYear(), getEndYear());
  }
}

// @Entity
class CarModel { // the Entity Model👑
  // @Id
  private Long id;
  private String make;
  private String model;
//  private int startYear;
//  private int endYear;
  // @Embedded // nu se modifica schema de baza de date
  private Interval yearInterval;

  protected CarModel() {
  } // for Hibernate

  public CarModel(String make, String model, int startYear, int endYear) {
    this.make = make;
    this.model = model;
    if (startYear > endYear) throw new IllegalArgumentException("start larger than end");
//    this.startYear = startYear;
//    this.endYear = endYear;
    yearInterval = new Interval(startYear, endYear);
  }

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

  public Interval yearInterval() {
    return new Interval(getStartYear(), getEndYear());
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