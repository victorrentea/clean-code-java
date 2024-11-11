package victor.training.cleancode;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

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

// Value Object = Immutable and lacking identity
// canonical example = Money{amount, currency}
// the more value objects, the better
/** Closed interval */
@Embeddable
record Interval(int start, int end) {
  Interval { // you will never see an invalid interval again in your life (immutable!!)
    // for select cases! // not for all the attrs and structures
    // examples: Order#status, Money#amount, Money#currencyx
    if (start > end) throw new IllegalArgumentException("start larger than end");
    // === start <= end
    // [1..3] OK
    // [-7..-3] OK
    // [-3..-7] NOT OK -> throws
  }
  /** comutative */
  public boolean intersects(Interval other) {
    return start <= other.end && other.start <= end;
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
class CarModel { // the Entity Model👑, private to my app. It is an arch goal to keep it private to my logic
  // DDD-style
  // @Id
  private Long id;
  private String make;
  private String model;
  // private BigDecimal transactionAmount;
  // private String transactionCurrency; // WTF!!! NO
  // private Money transactionAmount; // YES
//  private int startYear;
//  private int endYear;
  @Embedded // no change in the DB schema! no ALTER TABLE! (ORM ftw)
  private Interval yearInterval;

  protected CarModel() {
  } // for Hibernate

  public CarModel(String make, String model, int startYear, int endYear) {
    this.make = make;
    this.model = model;
    this.yearInterval = new Interval(startYear, endYear);
  }

  Interval getYearInterval() {
    return yearInterval;
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