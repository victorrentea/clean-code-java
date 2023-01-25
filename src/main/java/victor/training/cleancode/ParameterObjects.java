package victor.training.cleancode;

public class ParameterObjects {
   public static void main(String[] args) {
      PlaceOrderParams params = new PlaceOrderParams("John", "Doe", "St. Albergue", "Paris", 99);
      new ParameterObjects().placeOrder(params);
      new AnotherClass().otherMethod("John", "Doe", 17);
   }

   public void placeOrder(PlaceOrderParams placeOrderParams) {
      if (placeOrderParams.getfName() == null || placeOrderParams.getlName() == null) throw new IllegalArgumentException();

      System.out.println("Some Logic");
      System.out.println("Shipping to " + placeOrderParams.getCity() + " on St. " + placeOrderParams.getStreetName() + " " + placeOrderParams.getStreetNumber());

   }
}

class AnotherClass {
   public void otherMethod(String firstName, String lastName, int x) {
      if (firstName == null || lastName == null) throw new IllegalArgumentException();

      System.out.println("Another distant Logic " + x);
      System.out.println("Person: " + lastName);
   }
}

// Domain Entity
class Person {
   private Long id;
   private String firstName;
   private String lastName;
   private String phone;

   public Person(String firstName, String lastName) {
      if (firstName == null || lastName == null) throw new IllegalArgumentException();
      this.firstName = firstName;
      this.lastName = lastName;
   }

   // TODO hard-core: implement setter
   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getFirstName() {
      return firstName;
   }

   public String getLastName() {
      return lastName;
   }
}

class PersonService {
   public void f(Person person) {
      System.out.println("Hi there, " + person.getFirstName());

      String fullNameStr = person.getFirstName() + " " + person.getLastName().toUpperCase();
      System.out.println("Record for " + fullNameStr);
   }

   public void p(String streetName, String city, Integer streetNumber) {
      System.out.println("Living in " + city + " on St. " + streetName + " " + streetNumber);
   }

   public void pcaller() {
       p("Dristor", "Bucharest", 91);
   }
}