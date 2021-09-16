package victor.training.cleancode;

//@Entity
public class CarModel {
   //   @Id
   private Long id;
   private String make;
   private String model;
   private Interval yearInterval;

   private CarModel() {
   } // for Hibernate

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
