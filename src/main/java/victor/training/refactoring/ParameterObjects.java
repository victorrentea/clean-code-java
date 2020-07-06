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
      Address address = new Address("St. Albergue", "Paris", 99);
      new ParameterObjects().placeOrder(fullName, address);
   }

   public void placeOrder(FullName fullName, Address address) {
      System.out.println("Some Logic");
      System.out.println("Shipping to " + address.getCity() + " on St. " + address.getStreetName() + " " + address.getStreetNumber());
   }
}

//class Customer { // to generic, will grow enormously
//class Name { // to generic. a company also has a "name"
//class CustomerName { // to specific. a User also has first and last
// @Embeddable JPA funclub
class FullName { //perfect
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

   public String getFirstName() {
      return firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public String asEnterpriseName() {
      return firstName + " " + lastName.toUpperCase();
   }
}


class AnotherClass {
   public void otherMethod(FullName fullName, int x) {
      System.out.println("Another distant Logic " + x);
      System.out.println("Person: " + fullName.getLastName());
   }
}


// Champions League
// Holy entity

class Person {
   private Long id;
   // @Embedded
   private FullName fullName;
   private String phone;

   public Person(String firstName, String lastName) {
      fullName = new FullName(firstName, lastName);
   }

   // TODO hard-core: implement setter
//   public void setLastName(String lastName) {
//      this.lastName = lastName;
//   }

   public FullName getFullName() {
      return fullName;
   }
}

class PersonService {
   public void f(Person person) {
      System.out.println(person.getFullName().asEnterpriseName());
   }

   public void p(Address address) {
      System.out.println("Living in " + address.getCity() + " on St. " + address.getStreetName() + " " + address.getStreetNumber());
   }
}