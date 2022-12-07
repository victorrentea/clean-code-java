package victor.training.cleancode;


import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

class ExtractValueObjects {

  // see tests
  public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {
    List<CarModel> results = models.stream()
            .filter(model -> criteria.getInterval().intersects(model.getInterval()))
            .collect(Collectors.toList());
    System.out.println("More filtering logic");
    return results;
  }

  private void applyCapacityFilter() {
    System.out.println(new Interval(1000, 1600).intersects(new Interval(1250, 2000)));
  }

}

class Alta {
  private void applyCapacityFilter() {
    System.out.println(new Interval(1000, 1600).intersects(new Interval(1250, 2000)));
    System.out.println(new Interval(1000, 1600).intersects(new Interval(1250, 2000)));
  }

}

class MathUtil {

}


// Value Object (doar grupeaza niste date intre ele) nu DTO
// - imutabil (adica fara setteri)
// - nu are identitate persistenta (PK) - adica nu e @Entity
// - mic de obicei
// - ?hashCode equals pe toate campurile\

// record Interval(int start, int end) {}  - a la java 17

//@Value // >80% din din Java BE folosesc Lombok
//class Interval2 { // acceasi chestii dar generate integral de Lombok
//     int start;
//     int end;
//}
//@Embeddable
class Interval {
  private final int start;
  private final int end;

  public Interval(int start, int end) {
    if (start >= end) { // constrangeri de domeniu in modelu tau! PANICA!!
      // da si poate si pe @Entity,
      throw new IllegalArgumentException();
    }
    this.start = start;
    this.end = end;
  }

  // overload doar daca faci vreun cod de platforma, librarie, Utils reutilizabil
  public boolean intersects(Interval other) {
    return start <= other.end && other.start <= end;
  }

  public int getStart() {
    return start;
  }

  public int getEnd() {
    return end;
  }
}


class CarSearchCriteria { // smells like JSON ...
  private final int startYear;
  private final int endYear;
  private final String make;

  public CarSearchCriteria(int startYear, int endYear, String make) {
    this.make = make;
    if (startYear > endYear) throw new IllegalArgumentException("start larger than end");
    this.startYear = startYear;
    this.endYear = endYear;
  }

  @NotNull
  public Interval getInterval() {
    return new Interval(getStartYear(), getEndYear());
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

//@Entity
class CarModel { // the wholy Entity Model
  //   @Id
  private Long id;
  private String make;
  private String model;
  // DB tables nu trebuei impactate:=> @Embeddable (ORM)
  private Interval interval;

  protected CarModel() {
  } // for Hibernate

  public CarModel(String make, String model, Interval interval) {
    this.make = make;
    this.model = model;
    this.interval = interval;
  }

  public Interval getInterval() {
    return interval;
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


  @Override
  public String toString() {
    return "CarModel{" +
           "make='" + make + '\'' +
           ", model='" + model + '\'' +
           '}';
  }
}


class CarModelMapper {
  public CarModelDto toDto(CarModel carModel) {
    CarModelDto dto = new CarModelDto();
    dto.make = carModel.getMake();
    dto.model = carModel.getModel();
    dto.startYear = carModel.getInterval().getStart();
    dto.endYear = carModel.getInterval().getEnd();
    return dto;
  }

  public CarModel fromDto(CarModelDto dto) {
    return new CarModel(dto.make, dto.model, new Interval(dto.startYear, dto.endYear));
  }
}

class CarModelDto {
  public String make;
  public String model;
  public int startYear;
  public int endYear;
}