package victor.training.refactoring;

import lombok.NonNull;

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

   public String getLastName() {
      return lastName;
   }

   public String toEnterpriseName() {
      return firstName + " " + lastName.toUpperCase();
   }
}


// code of ANOTHER DEVELOPER OMG!
class AnotherClass {
   public void otherMethod(/*@NonNull*/ PersonName personName, int x) {
//      if (personName == null) {
//         throw new IllegalArgumentException();
//      }
      System.out.println("Another distant Logic " + x);
      m(personName);
   }

   private void m(PersonName personName) {
      n(personName);
   }

   private void n(PersonName personName) {
      p(personName);
   }

   private void p(PersonName personName) {
      System.out.println("Person: " + personName.getLastName());
   }
}


class Person {
   private Long id;
   //   @Embedded
   private PersonName name;
   private String phone;

   public Person(String firstName, String lastName) {
      this.name = new PersonName(firstName, lastName);
   }

   // TODO hard-core: implement setter
//   public void setLastName(String lastName) {
//      this.lastName = lastName;
//   }

   public PersonName getName() {
      return name;
   }
}

class PersonService {
   public void f(Person person) {
      System.out.println(person.getName().toEnterpriseName());
   }

   public void p(String city, String streetName, Integer streetNumber) {
      System.out.println("Living in " + city + " on St. " + streetName + " " + streetNumber);
   }
}