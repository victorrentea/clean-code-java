package victor.training.cleancode;

import io.vavr.Tuple2;
import io.vavr.Tuple4;

import java.util.ArrayList;
import java.util.List;

class ExtractValueObjects {

   // see tests
   public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {
      List<CarModel> results = new ArrayList<>(models);
      Interval criteriaInterval = new Interval(criteria.getStartYear(), criteria.getEndYear());
      results.removeIf(model -> !criteriaInterval.intersects(model.getYearInterval()));
//      results.removeIf(model -> !criteriaInterval.intersects(new Interval(model.getStartYear(), model.getEndYear())));
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


class IntersectParams {
   int start1,start2,end1,end2;
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
}



//@Entity
class CarModel {
//   @Id
   private Long id;
   private String make;
   private String model;
   private Interval yearInterval;

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
      dto.startYear = carModel.getYearInterval().getStart(); // x 1830
      dto.startYear = carModel.getYearInterval().getStart(); // x 1830
      dto.endYear = carModel.getYearInterval().getEnd();
      dto.endYear = carModel.getYearInterval().getEnd();
      dto.endYear = carModel.getYearInterval().getEnd();
      dto.endYear = carModel.getYearInterval().getEnd();
      dto.endYear = carModel.getYearInterval().getEnd();
      dto.endYear = carModel.getYearInterval().getEnd();
      dto.endYear = carModel.getYearInterval().getEnd();
      dto.endYear = carModel.getYearInterval().getEnd();
      dto.endYear = carModel.getYearInterval().getEnd();
      dto.endYear = carModel.getYearInterval().getEnd();
      dto.endYear = carModel.getYearInterval().getEnd();
      dto.endYear = carModel.getYearInterval().getEnd();
      dto.endYear = carModel.getYearInterval().getEnd();
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