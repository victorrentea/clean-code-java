package victor.training.cleancode;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

public class ManyParamsVO {
   public static void main(String[] args) {
      new ManyParamsVO().placeOrder(new FullName("John", "Doe"), "St. Albergue", "Paris", 99);
   }

   public void placeOrder(FullName fullName, String city, String streetName, Integer streetNumber) {
      System.out.println("Some Logic");
      System.out.println("Shipping to " + city + " on St. " + streetName + " " + streetNumber);

   }
}

class AnotherClass {
   {
      otherMethod(new FullName("a", "b"), 1);
   }
   public void otherMethod(FullName fullName, int x) {
      System.out.println("Another distant Logic " + x);
      System.out.println("Person: " + fullName.getLastName());
   }
}

class PersonClient {
   public static void main(String[] args) {
      Person person = new Person(new FullName("first", "last"));

      System.out.println(person.getFullName().getFirstName());
   }
}

// smell of an Entity: DATABASE
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

   // TODO hard-core: implement setter
   public void marry(Person person) {
      fullName = fullName.withLastName(person.getFullName().getLastName());
   }

   public FullName getFullName() {
      return fullName;
   }

}

class PersonService {
   public void f(Person person) {
      System.out.println("Hi there, " + person.getFullName().getFirstName());
      System.out.println("Hi there, " + person.getFullName().getFirstName());
      System.out.println("Hi there, " + person.getFullName().getFirstName());
      System.out.println("Hi there, " + person.getFullName().getFirstName());

//      person.getFirstName() + "  " + person.getLastName()

      String fullNameStr = person.getFullName().asEnterpriseName();
      System.out.println(person.getFullName().asEnterpriseName());
      System.out.println(person.getFullName().asEnterpriseName());
      System.out.println(person.getFullName().asEnterpriseName());
      System.out.println(person.getFullName().asEnterpriseName());
      System.out.println(person.getFullName().asEnterpriseName());
      System.out.println(person.getFullName().asEnterpriseName());
      System.out.println(person.getFullName().asEnterpriseName());
      System.out.println(person.getFullName().asEnterpriseName());
      System.out.println("Record for " + fullNameStr);
   }

   public void p(String streetName, String city, Integer streetNumber) {
      System.out.println("Living in " + city + " on St. " + streetName + " " + streetNumber);
   }

   public void pcaller() {
       p("Dristor", "Bucharest", 91);
   }
}