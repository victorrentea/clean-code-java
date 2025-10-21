package victor.training.cleancode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.AssertTrue;
import java.util.List;
import java.util.stream.Collectors;

class CarSearch {

  // run tests
  public List<CarModel> filterCarModels(
      CarSearchCriteria criteria, List<CarModel> carModels) {
    List<CarModel> results = carModels.stream()
        .filter(carModel -> criteria.getYears().intersects(carModel.getInterval()))
        .collect(Collectors.toList());
    System.out.println("More filtering logic ...");
    return results;
  }

}

class SomeOtherClientCode {
  private void applyLengthFilter() { // pretend
    System.out.println(new Interval(1000, 1600).intersects(
        new Interval(1250, 2000)
    ));
  }
  private void applyCapacityFilter() { // pretend
    System.out.println(new Interval(1000, 1600)
        .intersects(new Interval(1250, 2000)
    ));
  }
}

//record IntervalDates(int,int,int,int) {}
//record Interval(int) {}
//record DouaPerechiDeCapete(int) {}
//record OPerecheDeCapete(int,int) {}
//record PeriodDeIncadrare(int,int) {}
//record DOuaINtervale(int) {}
//record PatruAni(int,int,int,int) {}
//record Period(int startYear,int endYear) {}
record Interval(int startYear, int endYear) {
  Interval {
    if (startYear > endYear) throw new IllegalArgumentException("start larger than end");
  }

  @AssertTrue
  @JsonIgnore
  public boolean startIsBeforeEnd() {
    return startYear <= endYear;
  }

  public boolean intersects(Interval other) {
    return startYear <= other.endYear && other.startYear <= endYear;
  }
}

class IntervalUtil { // POO: UTILu te pica examenul
  //
  {
    new Interval(2007, 2002);
  }
}


class CarSearchCriteria { // a DTO received from JSON (REST API) = contract. NU ATINGE!! ca vine frontu/nemtii peste tine cu batele!
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

  @JsonIgnore
  public Interval getYears() {
    return new Interval(startYear, endYear);
  }
}

// @Entity
class CarModel { // the Entity Model👑 test
  // @Id
  private Long id;
  private String make;
  private String model;
  private Interval interval;

  protected CarModel() {
  } // for Hibernate

  public CarModel(String make, String model, int startYear, int endYear) {
    this.make = make;
    this.model = model;
    if (startYear > endYear) throw new IllegalArgumentException("start larger than end");
    this.interval = new Interval(startYear, endYear);
  }

  public Interval getInterval() {
    return interval;
  }

  public Long getId() {
    return id;
  }

  public int getEndYear() {
    return interval.endYear();
  }

  public int getStartYear() {
    return interval.startYear();
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
