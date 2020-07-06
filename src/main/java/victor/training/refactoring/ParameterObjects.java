package victor.training.refactoring;

import java.math.BigDecimal;

class Salary {
   private final BigDecimal amount;

   Salary(BigDecimal amount) {
      this.amount = amount;
   }
   public Salary increase(double percent) {
      return new Salary(amount.multiply(BigDecimal.valueOf(percent)));
   }
}

public class ParameterObjects {
   public static void main(String[] args) {
      FullName fullName = new FullName("John", "Doe");
      new ParameterObjects().placeOrder(fullName, "St. Albergue", "Paris", 99);
   }

   public void placeOrder(FullName fullName, String city, String streetName, Integer streetNumber) {
      if (fullName.getFirstName() == null || fullName.getLastName() == null) throw new IllegalArgumentException();

      System.out.println("Some Logic");
      System.out.println("Shipping to " + city + " on St. " + streetName + " " + streetNumber);
   }
}

//class Customer { // to generic, will grow enormously
//class Name { // to generic. a company also has a "name"
//class CustomerName { // to specific. a User also has first and last
class FullName { //perfect
   private final String firstName;
   private final String lastName;

   public FullName(String firstName, String lastName) {
      this.firstName = firstName;
      this.lastName = lastName;
   }

   public String getFirstName() {
      return firstName;
   }

   public String getLastName() {
      return lastName;
   }
}


class AnotherClass {
   public void otherMethod(String firstName, String lastName, int x) {
      if (firstName == null || lastName == null) throw new IllegalArgumentException();

      System.out.println("Another distant Logic " + x);
      System.out.println("Person: " + lastName);
   }
}

class Person {
   private Long id;
   private String firstName;
   private String lastName;
   private String phone;

   public Person(String firstName, String lastName) {
      this.firstName = firstName;
      this.lastName = lastName;
      if (firstName == null || lastName == null) throw new IllegalArgumentException();
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
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
      String fullNameStr = person.getFirstName() + " " + person.getLastName().toUpperCase();
      System.out.println(fullNameStr);
   }

   public void p(String city, String streetName, Integer streetNumber) {
      System.out.println("Living in " + city + " on St. " + streetName + " " + streetNumber);
   }
}