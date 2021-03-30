package victor.training.cleancode;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

public class ManyParamsVO {
   public static void main(String[] args) {
      Address address = new Address("St. Albergue", "Paris", 99);
      FullName fullName = new FullName("John", "Doe");
      new ManyParamsVO().placeOrder(fullName, address);
   }

   public void placeOrder(FullName fullName, Address address) {
      System.out.println("Some Logic");
      System.out.println("Shipping to " + address.getCity() + " on St. " + address.getStreetName() + " " + address.getStreetNumber());

   }
}

//class User, Person
//class User {
//class PersonWithHouse
//class PlaceOrderParams
//class OrderAddress

class AnotherClass {
   public void otherMethod(FullName fullName, int x) {
      System.out.println("Another distant Logic " + x);
      System.out.println("Person: " + fullName.getLastName());
   }
}

class ClientCode {
   public void method() {
      Person person = new Person(new FullName("John", "Doe"));
   }
}
// The Holy @Entity
@Entity
class Person {
   @Id
   private Long id;


   // TODO persist full name
   @Embedded
   private FullName fullName;

   private String phone;

   protected Person() {}
   public Person(FullName fullName) {
      this.fullName = fullName;
   }

   public void marry(Person him) {
      fullName = fullName.withLastName(him.getFullName().getLastName());
   }

   public FullName getFullName() {
      return fullName;
   }

}

class PersonService {
   public void f(Person person) {
      System.out.println("Hi there, " + person.getFullName().getFirstName());

      String fullNameStr = person.getFullName().getFirstName() + " " + person.getFullName().getLastName().toUpperCase();
      System.out.println("Record for " + fullNameStr);
   }

   public void p(Address address) {
      System.out.println("Living in " + address.getCity() + " on St. " + address.getStreetName() + " " + address.getStreetNumber());
   }

   public void pcaller() {
       p(new Address("Bucharest", "Dristor", 91));
   }
}