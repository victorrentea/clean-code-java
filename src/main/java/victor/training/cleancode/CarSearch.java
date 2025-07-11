package victor.training.cleancode;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;

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
    System.out.println(new Interval(1000, 1600).intersects(new Interval(1250, 2000)));
  }
  private void applyCapacityFilter() { // pretend
    System.out.println(new Interval(1000, 1600).intersects(new Interval(1250, 2000)));
  }
}

class MathUtil {
  // VECHE SI REA
}

// "Value Object" design pattern = un mic obiect IMUTABIL ce grupeaza date fara PK/id
@Embeddable
record Interval(int start, int end) {
  public Interval {
    if (start > end) throw new IllegalArgumentException("start larger than end");
  }

  //  public boolean isIntersectedWith(Interval other) {
  public boolean intersects(Interval other) {
//    repo.findAll()
    return start <= other.end && other.start <= end; // CPP de pe SO
  }
}


class CarSearchCriteria { // a DTO received from JSON
  private final int startYear;
  private final int endYear;
  //  private final int startPrice;
//  private final int endPrice;
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

  Interval getYearInterval() {
    return new Interval(getStartYear(), getEndYear());
  }
}

// @Entity
@Getter
class CarModel { // the Entity Model👑 test
  // @Id
  private Long id;
  private String make;
  private String model;
  @Embedded // nu necesita ALTER TABLE
  private Interval yearInterval;

  protected CarModel() {
  } // for Hibernate

  public CarModel(String make, String model, int startYear, int endYear) {
    this.make = make;
    this.model = model;
    this.yearInterval = new Interval(startYear, endYear);
  }

}

class CarModelMapper {
  public CarModelDto toDto(CarModel carModel) {
    CarModelDto dto = new CarModelDto();
    dto.make = carModel.getMake();
    dto.model = carModel.getModel();
    dto.startYear = carModel.getYearInterval().start();
    dto.endYear = carModel.getYearInterval().end();
    dto.endYear = carModel.getYearInterval().end();
    dto.endYear = carModel.getYearInterval().end();
    dto.endYear = carModel.getYearInterval().end();
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
