package victor.training.refactoring;

import org.junit.Assert;

import javax.persistence.*;
import java.util.Arrays;

public class ParameterObjects {
   public static void main(String[] args) {
      ParameterObjects target = new ParameterObjects();
      FullName fullName = new FullName("John", "Doe");
      target.placeOrder(fullName, "St. Albergue", "Paris", 99);
   }

   public void placeOrder(FullName fullName, String city, String streetName, Integer streetNumber) {

      System.out.println("Some Logic");
      System.out.println("Shipping to " + city + " on St. " + streetName + " " + streetNumber);

   }

}

//   class OrderRequest {} // too specific. Only usable in this workflow
//   class PlaceOrderRequest {} // too specific. Only usable in this workflow
//class Customer {} // too  vague. many developers will have ideas on what to add to this class
//class Name // silly. incorrect. company name doesnt have a first name
//class PersonName // too specific : user might also have first and last name
@Embeddable
class FullName {
   private String firstName;
   private String lastName;

   protected FullName() {}
   public FullName(String firstName, String lastName) {
      if (firstName == null || lastName == null) {
         throw new IllegalArgumentException();
      }
      this.firstName = firstName;
      this.lastName = lastName;
   }

   public String getLastName() {
      return lastName;
   }

   public String asEnterpriseName() { // feature envy
      return firstName + " " + lastName.toUpperCase();
   }

   @Override
   public String toString() {
      return "FullName{" +
          "firstName='" + firstName + '\'' +
          ", lastName='" + lastName + '\'' +
          '}';
   }

   public FullName withLastName(String newLastName) {
      return new FullName(firstName, newLastName);
   }
}


class AnotherClass {
   public void otherMethod(FullName fullName, int x) {

      System.out.println("Another distant Logic " + x);
      System.out.println("Person: " + fullName.getLastName());
   }
}

// Holy entity. Persistent data. Expect heavy use of this class throughout your codebase.
@Entity
class Person {
   @Id
   private Long id;
   @Embedded
   private FullName fullName;
   private String phone;

   public Person(FullName fullName) {
      this.fullName = fullName;
   }

//   // TODO hard-core: implement setter
   public void setLastName(String hisLastName) {
      fullName = fullName.withLastName(hisLastName);
   }

   public FullName getFullName() {
      return fullName;
   }
   public static double h() {
      return 1;
   }

}

class PersonService {
   public double g() {
       return 1 ;
   }
   public void f(Person person) {
      double x = 1;


      String fullNameStr = person.getFullName().asEnterpriseName();
      System.out.println(fullNameStr);
   }

   public void p(String city, String streetName, Integer streetNumber) {
      System.out.println("Living in " + city + " on St. " + streetName + " " + streetNumber);
   }
}