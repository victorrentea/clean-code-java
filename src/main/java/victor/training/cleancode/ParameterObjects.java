package victor.training.cleancode;

public class ParameterObjects {
   public static void main(String[] args) {
      new ParameterObjects().placeOrder(
              new FullName("John", "Doe"), new StreetAddress("St. Albergue", "Paris", 99));

      new AnotherClass().otherMethod(new FullName("John", "Doe"), 17);
   }

   public void placeOrder(FullName fullName, StreetAddress streetAddress) {
      if (fullName.firstName() == null || fullName.lastName() == null) throw new IllegalArgumentException();

      System.out.println("Some Logic");
      System.out.println("Shipping to " + streetAddress.city() + " on St. " + streetAddress.streetName() + " " + streetAddress.streetNumber());

   }
}

class AnotherClass {
   public void otherMethod(FullName fullName, int x) {
      if (fullName.getFirstName() == null || fullName.getLastName() == null) throw new IllegalArgumentException();

      System.out.println("Another distant Logic " + x);
      System.out.println("Person: " + fullName.getLastName());
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