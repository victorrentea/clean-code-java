package victor.training.cleancode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UtilsVsVO {
   // Ford Focus:     [2012 ---- 2016]
   // Search:              [2014 ---- 2018]
   public static void main(String[] args) {
      // can't afford a 2021 car
      CarSearchCriteria criteria = new CarSearchCriteria(2014, 2018, "Ford");
      CarModel fordFocusMk2 = new CarModel("Ford", "Focus", new Interval(2012, 2016));
      List<CarModel> models = new SearchEngine().filterCarModels(criteria, Arrays.asList(fordFocusMk2));
      System.out.println(models);
   }
}


class SearchEngine {


   // in 2010 i search for a car made between 2005 -    2008
   // Ford Focus Mk2 was made between 2002 -          2007

   public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {

      List<CarModel> results = models.stream()
          .filter(model -> criteria.matchesProductionYears(model.getYearInterval()))
          .collect(Collectors.toList());

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

@Embeddable
class Interval {
   private int start;
   private int end;
   protected  Interval() {} // Love for EclipseLink

   public Interval(int start, int end) {
      if (start > end) {
         throw new IllegalArgumentException("start > end");
      }
      this.start = start;
      this.end = end;
   }

   public boolean intersects(Interval other) {
      return start <= other.end && other.start <= end;
   }

   public int getStart() {
      return start;
   }

   public int getEnd() {
      return end;
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

   public Interval getYearInterval() {
      return new Interval(startYear, endYear);
   }

   public boolean matchesProductionYears(Interval yearInterval) {
      return getYearInterval().intersects(yearInterval);
   }
}

// THe HOLY ENTITY MODEL !!!!!!!
@Entity
class CarModel {
   @Id
   @GeneratedValue
   private Long id;
   private String make;
   private String model;

   @Embedded
   private Interval yearInterval; // in the same CAR_MODELS table

   protected CarModel() {}

   public CarModel(String make, String model, Interval yearInterval) {
      this.make = make;
      this.model = model;
      this.yearInterval = yearInterval;
   }

   public Interval getYearInterval() {
      return yearInterval;
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

