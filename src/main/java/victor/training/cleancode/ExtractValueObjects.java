package victor.training.cleancode;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExtractValueObjects {
   // Ford Focus:     [2012 ---- 2016]
   // Search:              [2014 ---- 2018]
   public static void main(String[] args) {
      // can't afford a 2021 car
      CarSearchCriteria criteria = new CarSearchCriteria(2014, 2018, "Ford");
      CarModel fordFocusMk2 = new CarModel("Ford", "Focus", 2012, 2016);
      List<CarModel> models = new SearchEngine().filterCarModels(criteria, Arrays.asList(fordFocusMk2));
      System.out.println(models);
   }
}


class SearchEngine {

   public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {
      List<CarModel> results = new ArrayList<>(models);
      results.removeIf(model -> !MathUtil.intervalsIntersect(
          criteria.getStartYear(), criteria.getEndYear(),
          model.getStartYear(), model.getEndYear()));
      System.out.println("More filtering logic");
      return results;
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
   private int startYear;
   private int endYear;

   private CarModel() {} // for Hibernate
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