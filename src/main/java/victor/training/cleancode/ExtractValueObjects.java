package victor.training.cleancode;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import java.util.List;
import java.util.stream.Collectors;

class ExtractValueObjects {

  // see tests
  public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {
    Interval criteriaInterval = new Interval(criteria.getStartYear(), criteria.getEndYear());
    List<CarModel> results = models.stream()
            .filter(model -> criteriaInterval.intersects(model.getYearInterval()))
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
  }

}

class MathUtil {

}


@Embeddable
class Interval {
  private int start;
  private int end;

  Interval(int start, int end) {
    this.start = start;
    this.end = end;
    // asta nu prea vezi in viata: face si testtarea mai comoplicata
    if (start > end) throw new IllegalArgumentException("start larger than end");
  }

  public Interval muta(int delta) {
    return new Interval(start + delta, end + delta);
  }

  @AssertTrue
  public boolean isCorrect() {
    return (start <= end);
  }

  public boolean intersects(Interval other) {
    return start <= other.end && other.start <= end; // direct de pe SO
  }

  public int getEnd() {
    return end;
  }

  public int getStart() {
    return start;
  }
}

class CarSearchCriteria { // smells like JSON ...
  private final int startYear;
  private final int endYear;
  private final String make;
//  private final Interval yearInterval; // API public !! PAZEA

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


// tre sa fie prietenu tau.
@Entity
class CarModel { // the holy Entity Model
  @Id
  private Long id;
  //  @EmbeddedId
  //  private CarModelId id;
  private String make;
  private String model;

  @Valid
  @Embedded // campurile din Interval ajung tot in tabela Car_MODEL
  private Interval yearInterval; // PK

  protected CarModel() {
  } // for Hibernate

  public CarModel(String make, String model, int startYear, int endYear) {
    this.make = make;
    this.model = model;
    if (startYear > endYear) throw new IllegalArgumentException("start larger than end");
    yearInterval = new Interval(startYear, endYear);
  }

  public Interval getYearInterval() {
    return yearInterval;
  }

  public Long getId() {
    return id;
  }

  // MIddle man = ai o metoda degeaba ce cheama alta metoda cu acelasi cu aceeasi parametri
  //  public int getEndYear() {
  //    return yearInterval.getEnd();
  //  }

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
    dto.startYear = carModel.getYearInterval().getStart();
    dto.endYear = carModel.getYearInterval().getEnd();
    //    dto.endYear = carModel.getYearInterval().getEnd();
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