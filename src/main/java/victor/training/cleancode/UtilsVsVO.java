package victor.training.cleancode;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class UtilsVsVO {
   // Ford Focus:     [2012 ---- 2016]
   // Search:              [2014 ---- 2018]

   public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {

      List<CarModel> results = models.stream()
          .filter(criteria::matchesYear)
          .collect(toList());

      System.out.println("More filtering logic");
      return results;
   }


   public void method() {
      System.out.println(new Interval(1, 3).intersects(new Interval(2, 4)));
   }

}

class Interval {
   private final int start;
   private final int end;

   public Interval(int start, int end) {
      if (start > end) throw new IllegalArgumentException("start larger than end");
      this.start = start;
      this.end = end;
   }

   public Interval translate(int delta) {
      return new Interval(start + delta,      end + delta);
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

class MateUtil {
   public static void main(String[] args) {
      new CarSearchCriteria("Ford", new Interval(2007, 2012));
   }
}


class CarSearchCriteria {
   private final Interval yearInterval;

   private final String make;

   public CarSearchCriteria(String make, Interval yearInterval) {
      this.make = make;
      this.yearInterval = yearInterval;
   }

   public String getMake() {
      return make;
   }

   public Interval getYearInterval() {
      return yearInterval;
   }

   public boolean matchesYear(CarModel model) {
      return yearInterval.intersects(model.getYearInterval());
   }
}

class CarModel {
   private final String make;
   private final String model;
   private final int startYear;
   private final int endYear;

   public CarModel(String make, String model, int startYear, int endYear) {
      this.make = make;
      this.model = model;
      if (startYear > endYear) throw new IllegalArgumentException("start larger than end");
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

   public Interval getYearInterval() {
      return new Interval(getStartYear(), getEndYear());
   }
}