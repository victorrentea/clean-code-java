package victor.training.cleancode;

public class ParameterObjects {
   public static void main(String[] args) {
      new ParameterObjects().placeOrder(new FullName("John", "Doe"), new Address("St. Albergue", "Paris", 17));
   }

   public void placeOrder(FullName fullName, Address address) {
      if (fullName.getFirstName() == null || fullName.getLastName() == null) throw new IllegalArgumentException();

      System.out.println("HEAVY LOGIC !$!(*%($*!(@&!(*$@!(");
      System.out.println("Recipient: " + fullName.asCorporateName());
      System.out.println("Shipping to " + address.getCity() + " on St. " + address.getStreetName() + " " + address.getStreetNumber());
   }

}

class AnotherClass {
   public void otherMethod(FullName fullName, int age) {
      if (fullName.getFirstName() == null || fullName.getLastName() == null) throw new IllegalArgumentException();

      System.out.println("Another distant Logic " + age);
      System.out.println("Hello mr " + fullName.getLastName());
   }
}

class Person {
   private Long id;
   private String phone;
   private FullName fullName;

   public Person(String firstName, String lastName) {
      fullName = new FullName(firstName, lastName);
   }

   public FullName getFullName() {
      return fullName;
   }
}

class PersonService {
   public void f(Person person) {
      System.out.println("Hi there, " + person.getFullName().getFirstName());


      String fullNameStr = person.getFullName().asCorporateName();
      System.out.println("Record for " + fullNameStr);
   }

   public void bar() {
      Person person = new Person("John", "Doe");
      System.out.println(person);
   }
}

class ClientCode {
   public void method() {
      new AnotherClass().otherMethod(new FullName("John", "Doe"), 9);
   }
}