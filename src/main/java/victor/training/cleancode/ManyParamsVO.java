package victor.training.cleancode;

import org.apache.commons.lang.StringUtils;

public class ManyParamsVO {
   public static void main(String[] args) {
      new ManyParamsVO().placeOrder(new FullName("John", "Doe"), "St. Albergue", "Paris", 99);
   }

   public void placeOrder(FullName fullName, String city, String streetName, Integer streetNumber) {

      System.out.println("Some Logic");
      System.out.println("Shipping to " + city + " on St. " + streetName + " " + streetNumber);

   }
}
//class Customer{ // prea general. va atrage sute de linii
//class CustomeProfile // mai rau
//class Name{ // incorect
//class PersonIdentity { // paru maciuca
class FullName {
   private final String firstName;
   private final String lastName;

   public FullName(String firstName, String lastName) {
      if (firstName == null || lastName == null) throw new IllegalArgumentException();
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

class Address {
//    String city, String streetName, Integer streetNumber
}

class AnotherClass {
   public void otherMethod(FullName fullName, int x) {

      System.out.println("Another distant Logic " + x);
      System.out.println("Person: " + fullName.getLastName());
   }
}

// @Entity =-cele mai pretioase lucruri
class Person {
   private Long id;
   private FullName fullName;
   private String phone;

   protected Person() {} // pt hibernate
   public Person(String firstName, String lastName, String phone) {
      setPhone(phone);
      fullName = new FullName(firstName, lastName);
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      if (StringUtils.isBlank(phone)) {
         throw new IllegalArgumentException();
      }
      this.phone = phone;
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
      String fullNameStr = person.getFullName().getFirstName() + " " + person.getFullName().getLastName().toUpperCase();
      System.out.println(fullNameStr);
   }

   public void p(String city, String streetName, Integer streetNumber) {
      System.out.println("Living in " + city + " on St. " + streetName + " " + streetNumber);
   }
}