package victor.training.cleancode;


import java.util.Arrays;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

public class UtilsVsVO {
   // Ford Focus:     [2012 ---- 2016]
   // Search:              [2014 ---- 2018]
   public static void main(String[] args) {
      // can't afford a 2021 car
      CarSearchCriteria criteria = new CarSearchCriteria(2014, 2018, "Ford");
//      CarModel fordFocusMk2 = new CarModel("Ford", "Focus", 2012, 2016);
      CarModel fordFocusMk2 = new CarModel("Ford", "Focus", new Interval(2012, 2016));
      List<CarModel> models = new SearchEngine().filterCarModels(criteria, Arrays.asList(fordFocusMk2));
      System.out.println(models);
   }
}


class SearchEngine {

   public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {
       Interval criteriaInterval = criteria.getYearInterval();
      return models.stream()
          .filter(model -> criteriaInterval.intersects(model.getYearInterval()))
          .collect(toList());
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

class Interval {
   private final Integer start;
   private final Integer end;

   Interval(Integer start, Integer end) {
      this.start = requireNonNull(start);
      this.end = requireNonNull(end);
      if (start > end) {
         throw new IllegalArgumentException("start larger than end");
      }
   }

   public boolean intersects(Interval other) {
      return start <= other.end && other.start <= end;
   }

   public Integer getStart() {
      return start;
   }

   public Integer getEnd() {
      return end;
   }
}









class CarSearchCriteria {
   private final int startYear;
   private final int endYear;
   private final String make;

   public CarSearchCriteria(int startYear, int endYear, String make) {
      this.make = make;
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

   public Interval getYearInterval() {
      return new Interval(getStartYear(), getEndYear());
   }
}

//@Entity
class CarModel {
//   @Id
   private Long id;
   private String make;
   private String model;
   private Interval yearInterval;

   public CarModel(String make, String model, Interval yearInterval) {
      this.make = make;
      this.model = model;
      this.yearInterval = yearInterval;
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

   public void setMake(String make) {
      this.make = make;
   }

   public void setModel(String model) {
      this.model = model;
   }

   @Override
   public String toString() {
      return "CarModel{" +
             "make='" + make + '\'' +
             ", model='" + model + '\'' +
             '}';
   }
}


class SomeClienCode {
   static {
      // some code
      CarModel model = null;


      System.out.println(model.getYearInterval().getStart() + " - " + model.getYearInterval().getEnd());
      if (model.getYearInterval().getStart() < 2015) {
         System.out.println("Old car");
      }
   }
}