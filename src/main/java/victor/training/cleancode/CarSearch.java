package victor.training.cleancode;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.stream.Collectors;

class CarSearch {
  // see tests
  public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> carModels) {
    Interval criteriaInterval = new Interval(criteria.getStartYear(), criteria.getEndYear());
    List<CarModel> results = carModels.stream()
        .filter(carModel -> criteriaInterval.intersects(carModel.getYearInterval()))
        .collect(Collectors.toList());
    System.out.println("More filtering logic ...");
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
  // veche naspa
}

class Interval {
  private final int start; // imutabila
  private final int end;

  public Interval(int start, int end) {
    // constrageri de biz pe obiecte
    if (start > end) throw new IllegalArgumentException("start larger than end! esti prost?");
    this.start = start;
    this.end = end;
  }

  // noua faina // keep behavior next to state
  public boolean intersects(Interval other) {
    return start <= other.end && other.start <= end;
    // logica ce opereaza exclusiv pe datele mele sta in clasa mea
     // ia param 0-1 arg simplu (string/int...)
  }

  // public void generateChartAxisPoints(JFreeChart chart, List<Point>, String sheet, boolean, ProductService NU) {
  // 30 linii de cod, cupleaza codul prea tare de chestii urate
  //}
  public int getEnd() {
    return end;
  }

  public int getStart() {
    return start;
  }

//  public  boolean intersects(Interval interval2) {
//    return this.getStart() <= interval2.getEnd() && interval2.getStart() <= this.getEnd();
//  }
}

// immutable 💖💖💖💖💖💖💖💖💖💖💖

//record Interval(int start, int end) {} // java 17 ftw

//@Data// ( din lombok)
//@Value// = @Data + campuri finale private
//class Interval {
//    int start;
//    int end;
//}


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

@Entity
class CarModel { // the holy Entity Model
  @Id
  private Long id;
  private String make;
  private String model;
//  private int startYear;
//  private int endYear;
  private Interval yearInterval;

  protected CarModel() {
  } // for Hibernate

  public CarModel(String make, String model, int startYear, int endYear) {
    this.make = make;
    this.model = model;
    yearInterval= new Interval(startYear, endYear);

   // cau masina produsa intre from:2022 .. to:2009. nu e  2009 > 2022!!!!
  }

  public Interval getYearInterval() {
    return yearInterval;
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
    dto.startYear = carModel.getYearInterval().getStart();
    dto.startYear = carModel.getYearInterval().getStart();
    dto.startYear = carModel.getYearInterval().getStart();
    dto.startYear = carModel.getYearInterval().getStart();
    dto.startYear = carModel.getYearInterval().getStart();
    dto.startYear = carModel.getYearInterval().getStart();
    dto.startYear = carModel.getYearInterval().getStart();
    dto.startYear = carModel.getYearInterval().getStart();
    dto.endYear = carModel.getYearInterval().getEnd();
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