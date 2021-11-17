package victor.training.cleancode;

import lombok.Value;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class ExtractValueObjects {
   // Ford Focus Mk2:     [2012 ---- 2016]
   // Search:                 [2014 ---- 2018]
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

      Interval criteriaInterval = new Interval(criteria.getStartYear(), criteria.getEndYear());
      List<CarModel> results = models.stream()
          .filter(model -> criteriaInterval.intersects(model.getYearInterval()))
          .collect(toList());
      System.out.println("More filtering logic");
      return results;
   }
   // Tarzan ; got hurt
   // baby steps
   // INLINE STATIC function to push in all code the body of our function
   // discovered Interval class from a large singature
   // we added logic to it!


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

//@Data // i hate data
@Value // i love this
class Interval {
   int start;
   int end;

   public boolean intersects(Interval other) {
      return start <= other.end && other.start <= end;
   }
}
//record Interval(int start, int end) {} java 17


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

// Holy Enty Model
//@Entity
class CarModel {
   //   @Id
   private Long id;
   private String make;
   private String model;
//   private int startYear;
//   private int endYear;
   private Interval yearInterval;

   private CarModel() {
   } // for Hibernate

   public CarModel(String make, String model, int startYear, int endYear) {  // TODO
      this.make = make;
      this.model = model;
      if (startYear > endYear) throw new IllegalArgumentException("start larger than end");  // TODO
//      this.startYear = startYear;
//      this.endYear = endYear;
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
   }  // TODO

   public int getStartYear() {
      return yearInterval.getStart();
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