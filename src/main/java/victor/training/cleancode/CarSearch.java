package victor.training.cleancode;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;
import java.util.stream.Collectors;

class CarSearch {

  // run tests
  public List<CarModel> filterCarModels(
      CarSearchCriteria criteria, // din JSON
      List<CarModel> carModels) {
    List<CarModel> results = carModels.stream()
        .filter(carModel -> criteria.yearInterval().intersectsWith(carModel.yearInterval()))
        .collect(Collectors.toList());
    System.out.println("More filtering logic ...");
    return results;
  }


  // filozofie: muta detaliile boring in clasele de pe langa, sa cureti ideea centrala
}

class SomeOtherClientCode {
  private void applyLengthFilter() { // pretend
    System.out.println(new Interval(1000, 1600).intersectsWith(new Interval(1250, 2000)));
  }
  private void applyCapacityFilter() { // pretend
    System.out.println(new Interval(1000, 1600).intersectsWith(new Interval(1250, 2000)));
  }
}


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

  public int getStartYear() {
    return startYear;
  }

  public int getEndYear() {
    return endYear;
  }

  public String getMake() {
    return make;
  }

  //  @JsonIgnore
  //atentie sa nu dai panica in colegi
  public Interval yearInterval() { // records ne-au scapat de un legacy Java de getter/setteri
    return new Interval(startYear, endYear);
  }
}

@Entity //Hibernate  = sfintele entitati pe care toata logica ta sprijina
class CarModel { // the Domain Entity Model👑
  @Id
  private Long id;
  private String make;
  private String model;
  // daca aplicatia ta are foarte mult creier. foarte complexe. multe reguli de biz,
  // (nu doar muta date)

  //  private int startYear;
//  private int endYear;
  @Embedded // NU AM NEVOIE NICI UN ALTER TABLE. in DB 1 tabela, in Java 2 obiecte.
  // ORMapper-ul stie sa faca mapping intre ele
  private Interval yearInterval;

  protected CarModel() {
  } // for Hibernate

  public CarModel(String make, String model, int startYear, int endYear) {
    this.make = make;
    this.model = model;

    this.yearInterval = new Interval(startYear, endYear);
//    this.startYear = startYear;
//    this.endYear = endYear;
  }

  public Interval yearInterval() {
    return yearInterval;//new Interval(startYear, endYear);
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
    dto.startYear = carModel.yearInterval().start();
    dto.startYear = carModel.yearInterval().start();
    dto.startYear = carModel.yearInterval().start();
    dto.startYear = carModel.yearInterval().start();
    dto.startYear = carModel.yearInterval().start();
    dto.startYear = carModel.yearInterval().start();
    dto.startYear = carModel.yearInterval().start();
    dto.startYear = carModel.yearInterval().start();
//    dto.endYear = carModel.getEndYear();
    dto.endYear = carModel.yearInterval().end();
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