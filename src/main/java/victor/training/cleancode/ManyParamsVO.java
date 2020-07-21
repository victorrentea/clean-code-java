package victor.training.cleancode;

public class ManyParamsVO {
   public static void main(String[] args) {
      new ManyParamsVO().placeOrder(new FullName("John", "Doe"), "St. Albergue", "Paris", 99);
   }

   public void placeOrder(FullName fullName, String city, String streetName, Integer streetNumber) {
      fullName.validate();

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

   public FullName(String fName, String lName) {
      firstName = fName;
      lastName = lName;
      validate();
   }

   public String getFirstName() {
      return firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void validate() {
      if (firstName == null || lastName == null) throw new IllegalArgumentException();
   }
}

class Address {
//    String city, String streetName, Integer streetNumber
}

class AnotherClass {
   public void otherMethod(FullName fullName, int x) {
      fullName.validate();

      System.out.println("Another distant Logic " + x);
      System.out.println("Person: " + fullName.getLastName());
   }
}

// @Entity =-cele mai pretioase lucruri
class Person {
   private Long id;
   private FullName fullName;
   private String phone;

   public Person(String firstName, String lastName) {

      fullName = new FullName(firstName, lastName);
      fullName.validate();
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