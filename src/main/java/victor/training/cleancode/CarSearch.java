package victor.training.cleancode;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

class CarSearch {

  // run tests
  public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> carModels) {
    List<CarModel> results = carModels.stream()
        .filter(carModel -> modelMatchesSearchedYears(criteria, carModel))
        .collect(Collectors.toList());
    System.out.println("More filtering logic ...");
    return results;
  }

  private boolean modelMatchesSearchedYears(CarSearchCriteria criteria, CarModel carModel) {
    int start1 = criteria.getStartYear();
    int end1 = criteria.getEndYear();
    int start2 = carModel.getYearInterval().start();
    int end2 = carModel.getYearInterval().end();
    return new YearInterval(start1, end1).intersects(new YearInterval(start2, end2));
  }
}

class SomeOtherClientCode {
  private void applyLengthFilter() { // pretend
    System.out.println(new YearInterval(1000, 1600).intersects(new YearInterval(1250, 2000)));
  }
  private void applyCapacityFilter() { // pretend
    System.out.println(new YearInterval(1000, 1600).intersects(new YearInterval(1250, 2000)));
  }
}

// = Value Object design pattern: grouping of data under a business name = immutable, without PK
// = Data Transfer Object (DTO) = move out of process (file,json,xml,sql)

// Keep It Simple, Stupid! - US NAVY
//record Interval(int start, int end) { // generic
record YearInterval(int start, int end) { // specific
  public YearInterval {// just javat2t2ttt; bulletproof anywhere you 'new'
    if (start > end) { // invariants
      throw new IllegalArgumentException("start larger than end");
    }
  }

  @AssertTrue // works if used as request DTO
  public boolean startLessThanEnd() {
    return start <= end;
  }

  public boolean intersects(YearInterval other) {
    // behavior on its state
    return start <= other.end && other.start <= end;
  }
}


class CarSearchCriteria { // a DTO received from JSON
  //
  private final int startYear;
  private final int endYear;
  //  private final YearInterval years;
  private final String make;

  public YearInterval getYearInterval() {
    return new YearInterval(startYear, endYear);
  }
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
class CarModel { // the Domain Model👑 test
  // @Id
  private Long id;
  @NotNull
  @Size(min = 3)
  private String make;
  @NotNull
  @Size(min = 3)
  private String model;
  //  private int startYear;
//  private int endYear;
  private YearInterval yearInterval;

  protected CarModel() {
  } // for Hibernate

  public CarModel(String make, String model, YearInterval yearInterval) {
    this.make = make;
    this.model = model;
    this.yearInterval = yearInterval;
  }

  public Long getId() {
    return id;
  }

  public YearInterval getYearInterval() {
    return yearInterval;
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
    dto.startYear = carModel.getYearInterval().start();
    dto.endYear = carModel.getYearInterval().end();
    return dto;
  }

  public CarModel fromDto(CarModelDto dto) {
    return new CarModel(dto.make, dto.model, new YearInterval(dto.startYear, dto.endYear));
  }
}

class CarModelDto {
  public String make;
  public String model;
  public int startYear;
  public int endYear;
}
