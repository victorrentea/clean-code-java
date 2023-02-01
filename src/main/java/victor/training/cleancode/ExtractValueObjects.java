package victor.training.cleancode;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.stream.Collectors;

class ExtractValueObjects {
  // see tests
  public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {
    List<CarModel> results = models.stream()
            .filter(model -> Interval.from(criteria).intersects(model.getYearInterval()))
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
//class Range {} // intotdeauna fortezi macar 2-3 variante pentru numele oricei clase/metode

class Interval {
  private final int start;
  private final int end;

  Interval(int start, int end) {
    if (start > end) throw new IllegalArgumentException("start larger than end");
    this.start = start;
    this.end = end;
  }

  public static Interval from(CarSearchCriteria criteria) { // OARE VREAU ASTA ?!
    return new Interval(criteria.getStartYear(), criteria.getEndYear());
  }

  public boolean intersects(Interval other) {
    return start <= other.end && other.start <= end;

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

// lucrurile cele mai enervante pentru devi
// - sedinte lungi
// - data migration scripts

@Entity
class CarModel { // the holy Entity Model
  @Id
  private Long id;
  private String make;
  private String model;
  private String f1;
  private String f2;
  private String f4;
  private String f5;
  private String f6;
  private String f7;
  private Interval yearInterval;

  public CarModel(String make, String model, int startYear, int endYear) {
    this.make = make;
    this.model = model;
    yearInterval = new Interval(startYear, endYear);
  }

  public Interval getYearInterval() {
    return yearInterval;
  }

  public Long getId() {
    return id;
  }

  public int getEndYear() {
    return yearInterval.getEnd();
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
    dto.startYear = carModel.getYearInterval() /// Niciodata cata vreme folosim reguli supide de formatting :)
            .getStart(); // cod din entity mai mic
    dto.endYear = carModel.getEndYear(); // e mai usor pt codu care foloseste modelu
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