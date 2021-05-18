package victor.training.cleancode;

import org.junit.Test;

public class ManyParamsVO {
   public static void main(String[] args) {
      new ManyParamsVO().placeOrder(new FullName("John", "Doe"), new Address("St. Albergue", "Paris", 99));
   }

   public void placeOrder(FullName fullName, Address address) {
      if (fullName.getFirstName() == null || fullName.getLastName() == null) throw new IllegalArgumentException();

      System.out.println("Some Logic");
      System.out.println("Shipping to " + address.getCity() + " on St. " + address.getStreetName() + " " + address.getStreetNumber());

   }
}


class AnotherClass {
   public void otherMethod(FullName fullName, int x) {
      if (fullName.getFirstName() == null || fullName.getLastName() == null) throw new IllegalArgumentException();

      System.out.println("Another distant Logic " + x);
      System.out.println("Person: " + fullName.getLastName());
   }
}

class Person {
   private Long id;
   private FullName fullName;
   private String phone;

   public Person(String firstName, String lastName) {
      this.fullName = new FullName(firstName, lastName);
   }

   // TODO hard-core: implement setter
   public void marry(Person him) {
      this.fullName = fullName.withLastName(him.getFullName().getLastName());
   }

   public Person setPhone(String phone) {
      this.phone = phone;
      return this;
   }

   public FullName getFullName() {
      return fullName;
   }



}
class StupidTest {

   @Test
   public void test() {

      Person person = new Person("John", "Doe")
          .setPhone("00000000");
   }
}

class PersonService {
   public void f(Person person) {
      System.out.println("Hi there, " + person.getFullName().getFirstName());

      String fullNameStr = person.getFullName().toCorporateName();
      System.out.println("Record for " + fullNameStr);
   }

   public void p(String streetName, String city, Integer streetNumber) {
      System.out.println("Living in " + city + " on St. " + streetName + " " + streetNumber);
   }

   public void pcaller() {
      p("Dristor", "Bucharest", 91);
   }
}