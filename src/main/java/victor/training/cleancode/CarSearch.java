package victor.training.cleancode;

import java.util.List;
import java.util.stream.Collectors;

class CarSearch {

  // run tests
  public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> carModels) {
    int a = 2;
    List<CarModel> results = carModels.stream()
        .filter(carModel -> criteria.getYearInterval().intersects(carModel.getYearInterval()))
        .collect(Collectors.toList());
//    criteria.getYearInterval().
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


record Interval(int start, int end) {
  Interval {
    if (start > end) throw new IllegalArgumentException("start larger than end"); // rar in industrie
  }
  //  public static boolean intersects(IntervalIntersectsParams ) {... prea specific ACESTEI fucnctii, non-reusable
  public boolean intersects(Interval other) { // BUNA
    return start <= other.end && other.start <= end;
  } // campurile sunt imutabile + sintaxa compacta

  public int length() { // synthetic getter, o valoare derivata din ce am campuri
    return end - start;
  }
}


class CarSearchCriteria /*extends Interval - NU */ { // a DTO received from JSON = FROZEN
  private final int startYear;
  private final int endYear;
  //  private final Interval yearInterval;// ti-ai rupt clienntii pentru ca ai schimbat OPENAPI-ul
  private final String make;

  public CarSearchCriteria(int startYear, int endYear, String make) {
    this.make = make;
    if (startYear > endYear) throw new IllegalArgumentException("start larger than end");
    this.startYear = startYear;
    this.endYear = endYear;
  }

  public Interval getYearInterval() {
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
//@Getter
class CarModel { // the Domain Entity Model👑
  // @Id
  private Long id;
  private String make;
  private String model;
  //  private int startYear;
//  private int endYear;
  // BUN pt ca -1 camp, +semantica
  private Interval yearInterval;// pot aici #potisitu pentru ca nimeni nu stie de structura asta!

  protected CarModel() {
  } // for Hibernate

  public CarModel(String make, String model, int startYear, int endYear) {
    this.make = make;
    this.model = model;
//    if (startYear > endYear) throw new IllegalArgumentException("start larger than end"); // business rule
//    this.startYear = startYear;
//    this.endYear = endYear;
    this.yearInterval = new Interval(startYear, endYear);
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

  public Interval getYearInterval() {
    return yearInterval;
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