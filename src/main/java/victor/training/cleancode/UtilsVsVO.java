package victor.training.cleancode;


import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

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
      return models.stream()
          .filter(model -> criteria.getYearInterval().intersect(model.getYearInterval()))
          .collect(toList());
   }

   private void applyCapacityFilter() {
      System.out.println(new Interval(1000, 1600).intersect(new Interval(1250, 2000)));
   }

}

class Alta {
   private void applyCapacityFilter() {
      System.out.println(new Interval(1000, 1600).intersect(new Interval(1250, 2000)));
   }

}
class AltUtil {

}
class MathUtil {

}

//@Embedded
class Interval {
   private int start;
   private int end;

   private Interval() {} // doar pt Hibernate
   Interval(int start, int end) {
      if (start > end) throw new IllegalArgumentException("start larger than end");
//      Interval.class.getDeclaredField("start").set(obj, 6);y

      this.start = start;
      this.end = end;
   }

   public boolean intersect(Interval other) {
      return start <= other.end && other.start <= end;
   }

   public int getEnd() {
      return end;
   }

   public int getStart() {
      return start;
   }

   public Interval shiftLeft(int delta) {
      return new Interval(getStart() - delta, getEnd() - delta);
   }
}
class CumTranslatezIntervalul {
   public void method(Interval interval) {
      Interval laStangaCu1 = interval.shiftLeft(1);
      System.out.println("Mutat la stanga cu 1: " + laStangaCu1);
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

@Entity
class CarModel {
      @Id
   private Long id;
   private String make;
   private String model;
//   private int startYear; // START_YEAR
//   private int endYear;
   @Embedded // JPA: face ca cele 2 campuri din yearInterval sa se adauge la tabela CAR_MODELS
   private Interval yearInterval ;

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
//      return yearInterval.getEnd();
//   }
//
//   public int getStartYear() {
//      return yearInterval.getStart();
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

class UndevaDeparte{
   public void method(CarModel carModel) {
      CarModelDto carModelDto = new CarModelDto();
      carModelDto.startYear = carModel.getYearInterval().getStart();
      carModelDto.endYear = carModel.getYearInterval().getEnd();
   }
}
class CarModelDto {
   public int startYear;

   public int endYear;
}