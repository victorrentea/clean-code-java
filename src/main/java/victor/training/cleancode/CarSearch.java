package victor.training.cleancode;

import java.util.List;

// TODO 3
class CarSearch {
  public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> carModels) {
    if (criteria.startYear() > criteria.endYear()) {
      throw new IllegalArgumentException("start larger than end");
    }
    List<CarModel> results = carModels.stream()
        .filter(carModel -> intervalsIntersect(criteria.yearInterval(), carModel.yearInterval()))
        .toList();
    System.out.println("Another use:" + intervalsIntersect(new Interval(1, 10), new Interval(5, 20)));
    return results;
  }

  // good new
  public static boolean intervalsIntersect(Interval interval1, Interval interval2) {
    return interval1.start() <= interval2.end() &&
           interval2.start() <= interval1.end();
  }
  // old bad
}

// part of my domain model = that secret set of data structure I use for most of my complexity
record Interval(int start, int end) {}

class MathUtil {

}

record CarSearchCriteria( // API JSON DTO
    int startYear,
    int endYear,
    String make,
    String model
) {
  public Interval yearInterval() {
    return new Interval(startYear(), endYear());
  }
}

class CarModel { // Domain Model👑
  private final String make;
  private final String model;
  private final int startYear;
  private final int endYear;

  public CarModel(String make, String model, int startYear, int endYear) {
    this.make = make;
    this.model = model;
    this.startYear = startYear;
    this.endYear = endYear;
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

  Interval yearInterval() {
    return new Interval(startYear, endYear);
  }
}

class ClientCode {
  public void someCode() {
    CarModel carModel = new CarModel("Ford", "Focus", 2012, 2016);
    System.out.println("code using " + carModel.getStartYear());
    System.out.println("code using " + carModel.getEndYear());
  }
}