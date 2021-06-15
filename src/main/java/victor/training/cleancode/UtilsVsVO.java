package victor.training.cleancode;

import java.util.Arrays;
import java.util.List;

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
//       Interval criteriaInterval = new Interval(criteria.getStartYear(), criteria.getEndYear());
      List<CarModel> results = models.stream()
          .filter(model -> criteria.getYearInterval().intersects(model.getYearInterval()))
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
//Interval::start   _.start
class Interval {
   private final int start;
   private final int end;

   Interval(int start, int end) {
      if (start > end) throw new IllegalArgumentException("start larger than end");
      this.start = start;
      this.end = end;
   }
   public int getLength() {
      return end-start;
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
}

//@Entity
class CarModel {
//   @Id
   private Long id;
   private String make;
   private String model;
//   private int startYear;
//   private int endYear;
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


//   public int getEndYear() {
//      return getYearInterval().getEnd();
//   }

//   public int getStartYear() {
//      return startYear;
//   }

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