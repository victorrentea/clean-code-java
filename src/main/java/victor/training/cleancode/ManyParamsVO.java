package victor.training.cleancode;

import static java.util.Objects.requireNonNull;

public class ManyParamsVO {
   public static void main(String[] args) {
      Address address = new Address("St. Albergue", "Paris", 99);
      FullName name = new FullName("John", "Doe");
      new ManyParamsVO().placeOrder(name, address);
   }

   public void placeOrder(FullName name, Address address) {
      System.out.println("Some Logic");
      System.out.println("Shipping to " + address.getCity() + " on St. " + address.getStreetName() + " " + address.getStreetNumber());
   }
}

//class Customer {
//class CustomerName {
//class OrderCustomer {
class FullName {
   private final String firstName;
   private final String lastName;

   public FullName(String firstName, String lastName) {
      this.firstName = requireNonNull(firstName);
      this.lastName = requireNonNull(lastName);
   }

   public String getFirstName() {
      return firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public String asInternationalName() {
      return firstName + " " + lastName.toUpperCase();
   }

   public FullName withLastName(String newLastName) {
      return new FullName(firstName, /*middleName,*/ newLastName);
   }
}


class AnotherClass {
   {
      otherMethod(new FullName("a", "b"), -1);
   }

   public void otherMethod(FullName fullName, int x) {
      System.out.println("Another distant Logic " + x);
      System.out.println("Person: " + fullName.getLastName());
      Person person = new Person(new FullName("Jane", "Doe"));
   }
}

// Mutable Entity!!
class Person {
   private Long id;
   private FullName fullName;
   private String phone;

   public Person(FullName fullName) {
      this.fullName = fullName;
   }


   // TODO hard-core: implement setter
   public void setLastName(String newLastName) {
      fullName = fullName.withLastName(newLastName);
   }

   public FullName getFullName() {
      return fullName;
   }

}

class PersonService {

   public void marita(Person ea, Person el) {
      if (true) {
         ea.setLastName(el.getFullName().getLastName());
      }
   }
   public void f(Person person) {
      System.out.println("Hi " + person.getFullName().getFirstName());


      String fullNameStr = person.getFullName().asInternationalName();
      System.out.println("Entries for " + fullNameStr);
   }

   public void p(Address address) {
      System.out.println("Living in " + address.getCity() + " on St. " + address.getStreetName() + " " + address.getStreetNumber());
   }
}