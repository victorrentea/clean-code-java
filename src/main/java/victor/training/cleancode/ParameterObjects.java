package victor.training.cleancode;

import io.vavr.Tuple2;
import io.vavr.Tuple3;

import java.time.LocalDate;
import java.util.function.BiFunction;

public class ParameterObjects {
   public static void main(String[] args) {
      new ParameterObjects().placeOrder(new FullName("John", "Doe"), new StreetAddress("St. Albergue", "Paris", 99));
   }

   public void placeOrder(FullName fullName, StreetAddress streetAddress) {
      if (fullName.getfName() == null || fullName.getlName() == null) throw new IllegalArgumentException();

//      var x;;// NICIODATA IN COD DE PROD!
//      Tuple2<BiFunction<String,Double, Tuple3<Long, Long, LocalDate>>, LocalDate> x = progFuctProstInteleasa;
      System.out.println("Some Logic");
      System.out.println("Shipping to " + streetAddress.getCity() + " on St. " + streetAddress.getStreetName() + " " + streetAddress.getStreetNumber());

   }
}

class AnotherClass {
   public void otherMethod(FullName fullName, int x) {
      if (fullName.getfName() == null || fullName.getlName() == null) throw new IllegalArgumentException();

      System.out.println("Another distant Logic " + x);
      System.out.println("Person: " + fullName.getlName());
   }
}

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