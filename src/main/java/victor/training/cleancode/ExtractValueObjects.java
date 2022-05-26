package victor.training.cleancode;

import io.vavr.Tuple6;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class ExtractValueObjects {

   // see tests
   public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {
      List<CarModel> results = new ArrayList<>(models);
      results.removeIf(model -> !new Interval(criteria.getStartYear(), criteria.getEndYear()).intersects(new Interval(model.getYearInterval().getStart(), model.getYearInterval().getEnd())));
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

//   public void method() {
//      Validator v;
//
//      v.validate(new CarModel())
//   }
}

class X {
   public void method() {
//      Tuple6<String, Long, Long, LocalDate, List<String>> t6;

//      t6.
   }
}

@Entity
class CarModel {
   @Id
   private Long id;
//   @NotNull
   private String make;
   private String model;
//   @Embedde
//   @AttributeOverrides({@AttributeOverride()})
   private Interval yearInterval;

   protected CarModel() {} // for Hibernate
   public CarModel(String make, String model, Interval yearInterval) {
      this.make = Objects.requireNonNull(make);
      this.model = model;
      this.yearInterval = yearInterval;
   }

   public Long getId() {
      return id;
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


class CarModelMapper {
   public CarModelDto toDto(CarModel carModel) {
      CarModelDto dto = new CarModelDto();
      dto.make = carModel.getMake();
      dto.model = carModel.getModel();
      dto.startYear = carModel.getYearInterval().getStart();
      dto.endYear = carModel.getYearInterval().getEnd();
      return dto;
   }
   public CarModel fromDto(CarModelDto dto) {
      return new CarModel(dto.make, dto.model, new Interval(dto.startYear, dto.endYear));
   }
}
class CarModelDto {
   public String make;
   public String model;
   public int startYear;
   public int endYear;
}