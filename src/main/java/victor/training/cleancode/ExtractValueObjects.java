package victor.training.cleancode;


import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

class ExtractValueObjects {

   // see tests
   public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {
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

@Embeddable //
class Interval {
   private int start;
   private int end;

   private Interval() {}
   public Interval(int start, int end) { // self-validating objects via constructors (and setters)
      if (start > end) throw new IllegalArgumentException("start larger than end");
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


class CarSearchCriteria { // stinks as JSON - DTO - garbage. API model. UGLY. BAD> EVIL
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
      return new Interval(getStartYear(), getEndYear());
   }
}

//@Entity
//@Data NEVER on entity
class CarModel {
   //   @Id
   private Long id;
   private String make;
   private String model;
   @Embedded
   private Interval years;

   private CarModel() {
   } // for Hibernate

   public CarModel(String make, String model, Interval years) {
      this.make = make;
      this.model = model;
      this.years = Objects.requireNonNull(years);
   }
   public Interval getYearInterval() {
      return years;
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
      dto.startYear = carModel.getYearInterval().getStart();
      dto.startYear = carModel.getYearInterval().getStart();
      dto.startYear = carModel.getYearInterval().getStart();
      dto.startYear = carModel.getYearInterval().getStart();
      dto.startYear = carModel.getYearInterval().getStart();
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