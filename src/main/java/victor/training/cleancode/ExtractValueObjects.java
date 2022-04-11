package victor.training.cleancode;

import java.util.List;
import java.util.stream.Collectors;

class ExtractValueObjects {

   // see tests
   public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {
      Interval criteriaInterval = new Interval(criteria.getStartYear(), criteria.getEndYear());
      List<CarModel> results = models.stream()
              .filter(model -> criteriaInterval.intersects(model.getYearInterval()))
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

class MathUtil {

}

class Interval { // 800 linii
   private final int start;
   private final int end;

   public Interval(int start, int end) {
      if (start > end) throw new IllegalArgumentException("start larger than end");

      this.start = start;
      this.end = end;
   }

   public int getStart() {
      return start;
   }
//   public int getStartRotunjitOFunctie() { // nu

   public boolean intersects(Interval other) {
      return start <= other.end && other.start <= end;
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
}

//@Entity
class CarModel {
//   @Id
   private Long id;
   private String make;
   private String model;

   private Interval yearInterval;// +
//   private int startYear; // -
//   private int endYear; // -

   private CarModel() {} // for Hibernate
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