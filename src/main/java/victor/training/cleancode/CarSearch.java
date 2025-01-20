package victor.training.cleancode;

import java.util.List;
import java.util.stream.Collectors;

class CarSearch {
  public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> carModels) {
    List<CarModel> results = carModels.stream()
        .filter(carModel -> criteria.getYearInterval().intersects(carModel.getProductionYears()))
        .collect(Collectors.toList());
    System.out.println("More filtering logic ...");
    return results;
  }

}

class SomeOtherClientCode {
  private void applyLength1Filter() { // pretend
    System.out.println(new Interval(1000, 1600).intersects(new Interval(1250, 2000)));
  }
  private void applyLengthFilter() { // pretend
    System.out.println(new Interval(1000, 1600).intersects(new Interval(1250, 2000)));
  }
  private void applyCapacityFilter() { // pretend
    Interval interval1 = new Interval(1000, 1600);
    Interval interval2 = new Interval(1250, 2000);
    System.out.println(interval1.intersects(interval2));
  }

}
//class MethodParams {
//  int a=1;
//  int b=2;
//  int c=3;
//  int d=4;
//
//  public void method() {
//    // logica ?
//  }
//
//}

// o clasa noua cu atribute declarate (cu tip), ideal IMUTABILA (stare nemodificabila dupa instantiere)
record Interval(int start, int end) {
  /**
   * comutativ operation
   */
  public boolean intersects(Interval other) {
    return start <= other.end && other.start <= end;
  }
}
//class Interval {
//  private final int start;
//  private final int end;
//
//  public Interval(int start, int end) {
//    this.start = start;
//    this.end = end;
//  }
//
//  public int getStart() {
//    return start;
//  }
//
//  public int getEnd() {
//    return end;
//  }
//}

class CarSearchCriteria { // a DTO received from JSON
  private final int startYear;
  private final int endYear;
  //  private final Interval interval; // nu ating structura pentru a nu-mi strica API public (REST)
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

  public Interval getYearInterval() {
    return new Interval(startYear, endYear);
  }

  public String getMake() {
    return make;
  }
}

// @Entity
class CarModel { // the Domain Model👑 = structura de date interna, PRIVATA aplicatiei, ascunsa inauntrul ei.
  // @Id
  private Long id;
  private String make;
  private String model;
  //  private int startYear;
//  private int endYear;
  private Interval productionYears;

  protected CarModel() {
  } // for Hibernate

  public CarModel(String make, String model, int startYear, int endYear) {
    this.make = make;
    this.model = model;
    if (startYear > endYear) throw new IllegalArgumentException("start larger than end");
//    this.startYear = startYear;
//    this.endYear = endYear;
    this.productionYears = new Interval(startYear, endYear);
  }

  public Interval getProductionYears() {
    return productionYears;
  }

  public Long getId() {
    return id;
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