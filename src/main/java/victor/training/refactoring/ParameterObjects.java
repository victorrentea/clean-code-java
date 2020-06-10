package victor.training.refactoring;

public class ParameterObjects {
   public static void main(String[] args) {
      ParameterObjects target = new ParameterObjects();
      FullName fullName = new FullName("John", "Doe");
      target.placeOrder(fullName, "St. Albergue", "Paris", 99);
   }

   public void placeOrder(FullName fullName, String city, String streetName, Integer streetNumber) {

      System.out.println("Some Logic");
      System.out.println("Shipping to " + city + " on St. " + streetName + " " + streetNumber);

   }

}
//   class OrderRequest {} // too specific. Only usable in this workflow
//   class PlaceOrderRequest {} // too specific. Only usable in this workflow
//class Customer {} // too  vague. many developers will have ideas on what to add to this class
//class Name // silly. incorrect. company name doesnt have a first name
//class PersonName // too specific : user might also have first and last name
class FullName {
   private final String firstName;
   private final String lastName;

   FullName(String firstName, String lastName) {
      if (firstName == null || lastName == null) {
         throw new IllegalArgumentException();
      }
      this.firstName = firstName;
      this.lastName = lastName;
   }

   public String getLastName() {
      return lastName;
   }

   public String getFirstName() {
      return firstName;
   }
}


class AnotherClass {
   public void otherMethod(FullName fullName, int x) {

      System.out.println("Another distant Logic " + x);
      System.out.println("Person: " + fullName.getLastName());
   }
}
// Holy entity. Persistent data. Expect heavy use of this class throughout your codebase.
class Person {
   private Long id;
   private final FullName fullName;
   private String phone;

   public Person(String firstName, String lastName) {
      fullName = new FullName(firstName, lastName);
   }

//   // TODO hard-core: implement setter
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