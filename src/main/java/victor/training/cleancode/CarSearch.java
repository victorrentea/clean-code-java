package victor.training.cleancode;

import java.util.List;

// TODO 3
class CarSearch {
  public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> carModels) {
    if (criteria.startYear() > criteria.endYear()) {
      throw new IllegalArgumentException("start larger than end");
    }
    List<CarModel> results = carModels.stream()
        .filter(carModel -> intervalsIntersect(
            criteria.startYear(), criteria.endYear(),
            carModel.getStartYear(), carModel.getEndYear()))
        .toList();
    System.out.println("Another use:" + intervalsIntersect(1, 10, 5, 20));
    return results;
  }

  public static boolean intervalsIntersect(int start1, int end1, int start2, int end2) {
    return start1 <= end2 && start2 <= end1;
  }
}

class MathUtil {

}

record CarSearchCriteria( // API JSON DTO
    int startYear,
    int endYear,
    String make,
    String model
) {}

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
}

class ClientCode {
  public void someCode() {
    CarModel carModel = new CarModel("Ford", "Focus", 2012, 2016);
    System.out.println("code using " + carModel.getStartYear());
    System.out.println("code using " + carModel.getEndYear());
  }
}