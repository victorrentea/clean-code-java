package victor.training.cleancode;

public class ManyParamsVO {
   public static void main(String[] args) {
      new ManyParamsVO().placeOrder(new FullName("John", "Doe"), "St. Albergue", "Paris", 99);
   }

   public void placeOrder(FullName fullName, String city, String streetName, Integer streetNumber) {
      if (fullName.getFirstName() == null || fullName.getLastName() == null) throw new IllegalArgumentException();

      System.out.println("Some Logic");
      System.out.println("Shipping to " + city + " on St. " + streetName + " " + streetNumber);

   }
}
//class Person {5} -- too broad
//class FullName []
//class Address {
//   String city, String streetName, Integer streetNumber
//}

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

   public Person(FullName fullName) {
      this.fullName = fullName;
   }

   public FullName getFullName() {
      return fullName;
   }

}

class Client {
   public static void main(String[] args) {

      Person person = new Person(new FullName("John", "Doe"));
   }
}

class PersonService {
   public void f(Person person) {
      System.out.println("Hi there, " + person.getFullName().getFirstName());
      System.out.println("Hi there, " + person.getFullName().getFirstName());
      System.out.println("Hi there, " + person.getFullName().getFirstName());
      System.out.println("Hi there, " + person.getFullName().getFirstName());
      System.out.println("Hi there, " + person.getFullName().getFirstName());

      String fullNameStr = person.getFullName().asEnterpriseName();


      System.out.println("Record for " + fullNameStr);
   }

   public void p(String streetName, String city, Integer streetNumber) {
      System.out.println("Living in " + city + " on St. " + streetName + " " + streetNumber);
   }

   public void pcaller() {
       p("Dristor", "Bucharest", 91);
   }
}