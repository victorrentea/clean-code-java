package victor.training.cleancode;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
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

   public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {
      final Interval criteriaInterval = new Interval(criteria.getStartYear(), criteria.getEndYear());

      List<CarModel> results = models.stream()
          .filter(model -> model.getYearInterval().intersects(criteriaInterval))
          .collect(Collectors.toList());

      System.out.println("More filtering logic");
      return results;
   }

   private void applyCapacityFilter() {
      Interval interval1 = new Interval(1000, 1600);
      Interval interval2 = new Interval(1250, 2000);
      System.out.println(interval1.intersects(interval2));
   }

}

class Alta {
   private void applyCapacityFilter() {
      System.out.println(new Interval(1000, 1600).intersects(new Interval(1250, 2000)));
   }
}

class MathUtil {

}

@Data
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
class Interval {
   private int start;
   private int end;
   protected Interval() {} // for hibernate

   public Interval(int start, int end) {
      if (start > end) throw new IllegalArgumentException("start larger than end");
      this.start = start;
      this.end = end;
   }

   public boolean intersects(Interval other) {
      return start <= other.end && other.start <= end;
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

@Entity
class CarModel {
   @Id
   private Long id;
   private  String make;
   private  String model;
   @Embedded
   private  Interval yearInterval;

   protected CarModel() {}
   public CarModel(String make, String model, Interval yearInterval) {
      this.make = make;
      this.model = model;
      this.yearInterval = yearInterval;
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

   public Interval getYearInterval() {
      return yearInterval;
   }
}