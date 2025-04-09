package victor.training.cleancode;

import jakarta.persistence.Embeddable;

import java.util.List;

class CarSearch {

  // run tests
  public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> carModels) {
    List<CarModel> results = carModels.stream()
            .filter(carModel -> criteria.getYearInterval().intersects(carModel.getYearInterval()))
            .toList();
    System.out.println("More filtering logic ...");
    return results;
  }
}

@Embeddable // cele 2 atribute vor deveni coloane in tabela CarModel
record Interval(int start, int end) { // immutable object: nu-si poate schimba starea dupa instantiere
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

  public int getStartYear() {
    return startYear;
  }

  public int getEndYear() {
    return endYear;
  }

  public String getMake() {
    return make;
  }

  public Interval getYearInterval() {
    return new Interval(startYear, endYear);
  }
}

// @Entity
class CarModel { // the Entity Model👑 test
  // @Id
  private Long id;
  private String make;
  private String model;
//  @Embedded
  private Interval yearInterval;

  protected CarModel() {
  } // for Hibernate

  public CarModel(String make, String model, int startYear, int endYear) {
    this.make = make;
    this.model = model;
    if (startYear > endYear) throw new IllegalArgumentException("start larger than end");
    this.yearInterval = new Interval(startYear, endYear);
  }

  public Interval getYearInterval() {
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

  // OCD zice: Cupleaza CarModel de CarSearchCriteria
  //  public boolean intersects(CarSearchCriteria criteria){
  //    return criteria.getStartYear() <= endYear && startYear <= criteria.getEndYear();
  //  }

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
