package victor.training.cleancode;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UtilsVsVO {
   // Ford Focus:     [2012 ---- 2016]
   // Search:              [2014 ---- 2018]
   public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {
      List<CarModel> results = models.stream()
          .filter(model -> model.producedBetweenYears(criteria.getYearInterval()))
          .collect(toList());
      System.out.println("More filtering logic");
      return results;
   }
}

class Interval {
   private final int start;
   private final int end;

   public Interval(int start, int end) {
      if (start > end) {
         throw new IllegalArgumentException("End < Start");
      }
      this.start = start;
      this.end = end;
   }

   public boolean intersects(Interval other) {
      return start <= other.end && other.start <= end;
   }
//	public String toHumanString() {
//		return "[" + start + " .. " + end + "]";
//	}
}

class Other {
   private void met() {
      Interval interval1 = new Interval(1, 3);
      Interval interval2 = new Interval(2, 4);
      boolean b = interval1.intersects(interval2);
      System.out.println(b);
   }
}

class CarSearchCriteria {
   //    private final int startYear;
//    private final int endYear;
   private Interval yearInterval;
   private final String make;

   public CarSearchCriteria(Interval yearInterval, String make) {
//    public CarSearchCriteria(int startYear, int endYear, String make) {
      this.make = make;
      this.yearInterval = yearInterval;
   }


   public String getMake() {
      return make;
   }

   public Interval getYearInterval() {
      return yearInterval;
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


   public boolean producedBetweenYears(Interval yearInterval) {
      return getYearInterval().intersects(yearInterval);
   }


   public String getMake() {
      return make;
   }

   public String getModel() {
      return model;
   }

   Interval getYearInterval() {
      return new Interval(startYear, endYear);
   }
}