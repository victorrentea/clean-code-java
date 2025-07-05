package victor.training.cleancode;

import java.util.List;
import java.util.stream.Collectors;

class CarSearch {
  public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> carModels) {
    List<CarModel> results = carModels.stream()
        .filter(carModel -> intervalsIntersect(
            criteria.startYear(), criteria.endYear(),
            carModel.getStartYear(), carModel.getEndYear()))
        .collect(Collectors.toList());
    System.out.println("More filtering logic ...");
    return results;
  }

  public static boolean intervalsIntersect(int start1, int end1, int start2, int end2) {
    return start1 <= end2 && start2 <= end1;
  }
}

record CarSearchCriteria(
    int startYear,
    int endYear,
    String make,
    String model
) {
  CarSearchCriteria {
    if (startYear > endYear) throw new IllegalArgumentException("start larger than end");
  }
}

class CarModel { // the Domain Model👑
  private Long id;
  private String make;
  private String model;
  private int startYear;
  private int endYear;

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
}

class CarModelMapper {
  public void someCode() {
    CarModel carModel = new CarModel("Ford", "Focus", 2012, 2016);
    System.out.println("code using " + carModel.getStartYear());
    System.out.println("code using " + carModel.getEndYear());
  }
}