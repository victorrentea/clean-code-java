package victor.training.cleancode;

import jakarta.persistence.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
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
    h();
  }

  private void h() {
    g();
  }

  private void g() {
    f();
  }

  public void f() {

  }
  private void applyCapacityFilter() { // pretend
    System.out.println(new Interval(1000, 1600).intersects(new Interval(1250, 2000)));
  }
}

class MathUtil {
}
@Embeddable
record Interval(int start, int end) {
  Interval {
    // ajuta mult daca ai logica in app foarte complicata
    if (start > end) throw new IllegalArgumentException("start larger than end");
  }
  public boolean intersects(Interval other) {
    return start <= other.end && other.start <= end;
  }
}


class CarSearchCriteria { // a DTO received from JSON
  private final int startYear;
  private final int endYear;
//  private final Interval yearInterval;// stric API modelul
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

 @Entity
class CarModel { // the Entity Model👑
   @Id
  private Long id;
   @NotNull
  private String make;
  private String model;
//  private int startYear; // CAR_MODEL.START_YEAR
//  private int endYear;
   @Embedded // nu se modifica TABELA din DB + @AttributeOverrides
   @AttributeOverrides({
        @AttributeOverride(name = "start", column = @Column(name = "start_year")),
        @AttributeOverride(name = "end", column = @Column(name = "end_year"))
   })
   private Interval yearInterval; // -1 camp = dev happy ++

  protected CarModel() {
  } // for Hibernate

  public CarModel(String make, String model, int startYear, int endYear) {
    this.make = Objects.requireNonNull(make);
    this.model = model;
    if (startYear > endYear) throw new IllegalArgumentException("start larger than end");
    this.yearInterval = new Interval(startYear, endYear);
  }

  public Long getId() {
    return id;
  }

   public Interval getYearInterval() {
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
    dto.startYear = carModel.getYearInterval().start();
    dto.startYear = carModel.getYearInterval().start();
    dto.startYear = carModel.getYearInterval().start();
    dto.startYear = carModel.getYearInterval().start();
    dto.startYear = carModel.getYearInterval().start();
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