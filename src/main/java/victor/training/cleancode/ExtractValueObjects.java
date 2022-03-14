package victor.training.cleancode;


import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

class ExtractValueObjects {

   // see tests
   public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {
      List<CarModel> results = models.stream()
          .filter(model -> filterByYears(criteria, model))
          .collect(toList());
      System.out.println("More filtering logic");
      return results;
   }

   private boolean filterByYears(CarSearchCriteria criteria, CarModel model) {
      return MathUtil.intervalsIntersect(
          criteria.getStartYear(), criteria.getEndYear(),
          model.getStartYear(), model.getEndYear());
   }

   private void applyCapacityFilter() {
      System.out.println(MathUtil.intervalsIntersect(1000, 1600, 1250, 2000));
   }

}

class Alta {
   private void applyCapacityFilter() {
      System.out.println(MathUtil.intervalsIntersect(1000, 1600, 1250, 2000));
   }

}

class MathUtil {

   public static boolean intervalsIntersect(int start1, int end1, int start2, int end2) {
      return start1 <= end2 && start2 <= end1;
   }
}
class Interval {
   int start1;
   int end1;
}


class CarSearchCriteria { // stinks as JSON - DTO - garbage. API model. UGLY. BAD> EVIL
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
   private int startYear;
   private int endYear;

   private CarModel() {
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