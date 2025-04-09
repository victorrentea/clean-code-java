package victor.training.cleancode;

import java.util.List;

class CarSearch {

  // run tests
  public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> carModels) {
    List<CarModel> results =
        carModels.stream()
            //        .filter(carModel -> carModel.intersects(criteria.getStartYear(),
            // criteria.getEndYear()))
            //        .filter(carModel -> carModel.intersects(criteria)) // <- coupling
            //        .filter(carModel -> carModel.intersects({start:criteria.start,
            // end:criteria.end})) // <- TS
            .filter(
                carModel ->
                    MathUtil.intervalsIntersect(
                        new Interval(criteria.getStartYear(), criteria.getEndYear()),
                        new Interval(carModel.getStartYear(), carModel.getEndYear())))
            .toList();
    System.out.println("More filtering logic ...");
    return results;
  }
  // - metoda in plus care trebuie si ea inteleasa
  // - metoda intervalsIntersect tot ia 4 param->urata pentru toti altii
  //   private boolean yearIntervalsIntersect(CarSearchCriteria criteria, CarModel carModel) {
  //    return MathUtil.intervalsIntersect(
  //        criteria.getStartYear(), criteria.getEndYear(),
  //        carModel.getStartYear(), carModel.getEndYear());
  //  }
}

// class SomeOtherClientCode {
//  private void applyLengthFilter() { // pretend
//    System.out.println(MathUtil.intervalsIntersect(1000, 1600, 1250, 2000));
//  }
//  private void applyCapacityFilter() { // pretend
//    System.out.println(MathUtil.intervalsIntersect(1000, 1600, 1250, 2000));
//  }
// }

class MathUtil {
  public static boolean intervalsIntersect(Interval interval1, Interval interval2) {
    return interval1.start <= interval2.end && interval2.start <= interval1.end;
  }
}

class Interval {
  int start;
  int end;

  public Interval(int start, int end) {
    this.start = start;
    this.end = end;
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
}

// @Entity
class CarModel { // the Entity Model👑 test
  // @Id
  private Long id;
  private String make;
  private String model;
  private int startYear;
  private int endYear;

  protected CarModel() {
  } // for Hibernate

  public CarModel(String make, String model, int startYear, int endYear) {
    this.make = make;
    this.model = model;
    if (startYear > endYear) throw new IllegalArgumentException("start larger than end");
    this.startYear = startYear;
    this.endYear = endYear;
  }

  public Long getId() {
    return id;
  }

  public int getEndYear() {
    return endYear;
  }

  public int getStartYear() {
    return startYear;
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
