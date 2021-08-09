package victor.training.cleancode;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ExtractValueObjects {
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
      List<CarModel> results = models.stream().filter(model ->
              new Interval(criteria.getStartYear(), criteria.getEndYear())
                  .intersect(model.getYearInterval()))
          .collect(Collectors.toList());
      System.out.println("More filtering logic");
      return results;
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

class MathUtil {

}


@Data
@Setter(AccessLevel.NONE)
@Embeddable
class Interval { // VALUE OBJECT. HASHCODE EQUALS are implemented based on all fields.
   // usually are best if served immutable.
   private int start;
   private int end;

   protected Interval() {} // hibernate!
   public Interval(int start, int end) {
      if (start > end) {
         throw new IllegalArgumentException("start larger than end");
      }
      this.start = start;
      this.end = end;
   }

   public boolean intersect(Interval interval2) {
      return start <= interval2.end && interval2.start <= end;
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
   private String make;
   private String model;

   @Embedded
   private Interval yearInterval;

//   private int startYear;
//   private int endYear;

   protected CarModel() {} // for Hibernate
   public CarModel(String make, String model, Interval yearInterval) {
      this.make = make;
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