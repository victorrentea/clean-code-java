package victor.training.cleancode;


import javax.persistence.Embedded;
import java.util.List;

import static java.util.stream.Collectors.toList;

class ExtractValueObjects {

   // see tests
   public List<CarModel> filterCarModels(List<CarModel> models, Range criteriaRange) {
      List<CarModel> results = models.stream()
          .filter(model -> criteriaRange.intersects(model.getYearRange()))
          .collect(toList());
      System.out.println("More filtering logic");
      return results;
   }

   private void applyCapacityFilter() {
      System.out.println(new Range(1000, 1600).intersects(new Range(1250, 2000)));
   }

}

class Alta {
   private void applyCapacityFilter() {
      System.out.println(new Range(1000, 1600).intersects(new Range(1250, 2000)));
   }

}

class MathUtil {
}

class Range {
   private final int start;
   private final int end;

   public Range(int start, int end) {
      if (start > end) { // model self-validat. ATENTIE LA BUGURI. oricine folosea acest Range, va primit ex daca nu era valid
         throw new IllegalArgumentException("start larger than end");
      }
      this.start = start;
      this.end = end;
   }

   public boolean intersects(Range other) {
      return start <= other.end && other.start <= end;
   }

   public int getEnd() {
      return end;
   }

   public int getStart() {
      return start;
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
   @Embedded // gen
   private Range yearRange;

   private CarModel() {
   } // for Hibernate

   public CarModel(String make, String model, Range yearRange) {
      this.make = make;
      this.model = model;
      this.yearRange = yearRange;
   }

   public Range getYearRange() {
      return yearRange;
   }

   public Long getId() {
      return id;
   }
   // code smell: man in the middle: cod stupid care doar delega mai departe, fara zica nimic in plus
   //   public int getStartYear() {
   //      return getYearRange().getStart();
   //   }

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
      dto.startYear = carModel.getYearRange().getStart();
      dto.endYear = carModel.getYearRange().getEnd();
      dto.endYear = carModel.getYearRange().getEnd();
      dto.endYear = carModel.getYearRange().getEnd();
      dto.endYear = carModel.getYearRange().getEnd();
      dto.endYear = carModel.getYearRange().getEnd();
      dto.endYear = carModel.getYearRange().getEnd();
      dto.endYear = carModel.getYearRange().getEnd();
      return dto;
   }

   public CarModel fromDto(CarModelDto dto) {
      return new CarModel(dto.make, dto.model, new Range(dto.startYear, dto.endYear));
   }
}

class CarModelDto {
   public String make;
   public String model;
   public int startYear;
   public int endYear;
}