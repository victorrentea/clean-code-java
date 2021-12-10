package victor.training.cleancode;

public class ParameterObjects {
   public static void main(String[] args) {
      new ParameterObjects().placeOrder("John", "Doe", "St. Albergue", "Paris", 17);
   }

   public void placeOrder(String firstName, String lastName, String city, String streetName, Integer streetNumber) {
      if (firstName == null || lastName == null) throw new IllegalArgumentException();

      System.out.println("HEAVY LOGIC !$!(*%($*!(@&!(*$@!(");
      System.out.println("Recipient: " + firstName + " " + lastName.toUpperCase());
      System.out.println("Shipping to " + city + " on St. " + streetName + " " + streetNumber);
   }
}

class AnotherClass {
   public void otherMethod(String firstName, String lastName, int age) {
      if (firstName == null || lastName == null) throw new IllegalArgumentException();

      System.out.println("Another distant Logic " + age);
      System.out.println("Hello mr " + lastName);
   }
}

class Person {
   private Long id;
   private String phone;
   private String firstName;
   private String lastName;

   public Person(String firstName, String lastName) {
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

class PersonService {
   public void f(Person person) {
      System.out.println("Hi there, " + person.getFirstName());

      String fullNameStr = person.getFirstName() + " " + person.getLastName().toUpperCase();
      System.out.println("Record for " + fullNameStr);
   }

   public void bar() {
      Person person = new Person("John", "Doe");
      System.out.println(person);
   }
}

class ClientCode {
   public void method() {
      new AnotherClass().otherMethod("John", "Doe", 9);
   }
}