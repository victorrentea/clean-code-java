package victor.training.cleancode;

import java.util.List;

import static java.util.stream.Collectors.toList;

class ExtractValueObjects {

   // see tests
   public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {
      Interval criteriaInterval = new Interval(criteria.getStartYear(), criteria.getEndYear());
      List<CarModel> results = models.stream()
          .filter(model -> criteriaInterval.intersects(model.getProductionYears()))
          .collect(toList());
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












class CarSearchCriteria {
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

//@Entity
class CarModel {
//   @Id
   private Long id;
   private String make;
   private String model;
   private Interval productionYears;

   private CarModel() {} // for Hibernate
   public CarModel(String make, String model, Interval productionYears) {
      this.make = make;
      this.model = model;
      this.productionYears = productionYears;
   }

   public Interval getProductionYears() {
      return productionYears;
   }

   // man in the middle
//   public int getStartYear() {
//      return productionYears.getStart();
//   }
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
      dto.startYear = carModel.getProductionYears().getStart();
      dto.endYear = carModel.getProductionYears().getEnd();
      dto.endYear = carModel.getProductionYears().getEnd();
      dto.endYear = carModel.getProductionYears().getEnd();
      dto.endYear = carModel.getProductionYears().getEnd();
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