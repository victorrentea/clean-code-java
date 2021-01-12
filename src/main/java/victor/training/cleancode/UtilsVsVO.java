package victor.training.cleancode;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static victor.training.refactoring.MoveStatementsInOutFunctions.f;

public class UtilsVsVO {
   // Ford Focus Mk2:     [2012 ---- 2016]
   // Search:                  [2014 ---- 2018]
   public static void main(String[] args) {
      // can't afford a 2021 car
      CarSearchCriteria criteria = new CarSearchCriteria(2014, 2018, "Ford");
      CarModel fordFocusMk2 = new CarModel("Ford", "Focus", new Interval(2012, 2016));
      List<CarModel> models = new SearchEngine().filterCarModels(criteria, Arrays.asList(fordFocusMk2));
      System.out.println(models);
      A1 a1 = new A1(new B1(null));



      String s;

       s =  a1.getB().getC().map(c -> c.getName().toUpperCase()).orElse("");
   }
//   public void method(long customerId) {
//       g(customerId);
//   }
//
//   private void g(Long customerId) {
//      h(customerId, "str");
//   }
//
//   private void h(Long customerId, String str) {
//      repo.findById(customerId);
//   }
}

class A1 {
   private final B1 b; // NOT NULL

   A1(B1 b) {
      this.b = Objects.requireNonNull(b);
   }

   public B1 getB() {
      return b;
   }
}
class B1 {
   private final C c; // doar asta poate sa fie NULL

   B1(C c) {
      this.c = c;
   }

   public Optional<C> getC() {
      return Optional.ofNullable(c);
   }
}
class C {
   private String name; // NOT NULL
//   private int age;
   C(String name) {
      setName(name);
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = Objects.requireNonNull(name);
   }
}

class SearchEngine {


   public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {

      List<CarModel> filtered = models.stream()
          .filter(model -> model.getInterval().intersects(criteria.getInterval()))
          .collect(Collectors.toList());

      List<String> rez = filtered.stream().map(CarModel::toString).collect(Collectors.toList());

      List<CarModel> results = filtered.stream()
//          .map
          .collect(Collectors.toList()); /// END 2

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

   Interval getInterval() {
      return new Interval(getStartYear(), getEndYear());
   }
}

class CarModel {
   private final String make;
   private final String model;
   private final Interval yearInterval;

   public CarModel(String make, String model, Interval yearInterval) {
      this.make = make;
      this.model = model;
      this.yearInterval = yearInterval;
   }

   public Interval getInterval() {
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

   public void faSideEffect() {

   }
}