package victor.training.cleancode;

import javax.validation.Valid;

public class ManyParamsVO {
   public static void main(String[] args) {
      FullName fullName = new FullName("John", "Doe");
      Address address = new Address("St. Albergue", "Paris", 99);
      new ManyParamsVO().placeOrder(fullName, address);
   }

   public void placeOrder(FullName fullName, Address address) {
      System.out.println("Some Logic");
      System.out.println("Shipping to " + address.getCity() + " on St. " + address.getStreetName() + " " + address.getStreetNumber());

   }

}

//class Name { gresit. prea vag si prea general
class FullName {
   private final String firstName;
   private final String lastName;

   public FullName(String firstName, String lastName) {
      this.firstName = firstName;
      this.lastName = lastName;
      if (firstName == null || lastName == null) {
         throw new IllegalArgumentException();
      }
   }
   public String getFirstName() {
      return firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public String toEnterpriseName() {
      return firstName + " " + lastName.toUpperCase();
   }

   public FullName withLastName(String newLastName) {
      return new FullName(firstName, newLastName);
   }
}

class AnotherClass {
   // nu e codu tau si totusi il imbunatatesti
   public void otherMethod(FullName fullName, int x) {
      System.out.println("Another distant Logic " + x);
      System.out.println("Person: " + fullName.getLastName());
   }
}

//@Entity
class Person {
   private Long id;
   @Valid
   private FullName fullName;
   private String phone;

   public Person(String firstName, String lastName) {
      this.fullName = new FullName(firstName, lastName);
   }


   // TODO hard-core: implement setter
//   public void setLastName(String lastName) {
//      this.lastName = lastName;
//   }
   public void marita(Person sotz) {
      fullName = fullName.withLastName(sotz.fullName.getLastName());
   }
   public FullName getFullName() {
      return fullName;
   }

}

class PersonService {
   public void f(Person person) {
      System.out.println(person.getFullName().toEnterpriseName());
   }

   public void p(Address address) {
      System.out.println("Living in " + address.getCity() + " on St. " + address.getStreetName() + " " + address.getStreetNumber());
   }
}