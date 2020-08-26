package victor.training.refactoring;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

public class ParameterObjects {
   public static void main(String[] args) {
      PersonName name = new PersonName("John", "Doe");
      new ParameterObjects().placeOrder(name, "St. Albergue", "Paris", 99);
   }

   public void placeOrder(PersonName name, String city, String streetName, Integer streetNumber) {
      System.out.println("Some Logic");
      System.out.println("Shipping to " + city + " on St. " + streetName + " " + streetNumber);

   }
}

//class OrderPlaceRequest { // too specific. Overengineering: won't ever be reused. It's just a blind action.
//class Person { // overly generic, can explode with too much data.
//class Customer {// overly generic, can explode with too much data.
//class Name { // not precise. A company has a Name too
//@Data :)
// record java 13

//@Embeddable
class PersonName { // not precise. A company has a Name too
   private final String firstName;
   private final String lastName;

   public PersonName(String firstName, String lastName) {
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
}


// code of ANOTHER DEVELOPER OMG!
class AnotherClass {
   public void otherMethod(PersonName personName, int x) {
      System.out.println("Another distant Logic " + x);
      System.out.println("Person: " + personName.getLastName());
   }
}


class Person {
   private Long id;
   //   @Embedded
   private PersonName personName;
   private String phone;

   public Person(String firstName, String lastName) {
      this.personName = new PersonName(firstName, lastName);
   }

   // TODO hard-core: implement setter
//   public void setLastName(String lastName) {
//      this.lastName = lastName;
//   }

   public String getFirstName() {
      return personName.getFirstName();
   }

   public String getLastName() {
      return personName.getLastName();
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