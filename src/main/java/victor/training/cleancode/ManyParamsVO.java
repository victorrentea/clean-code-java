package victor.training.cleancode;

public class ManyParamsVO {
   public static void main(String[] args) {
      new ManyParamsVO().placeOrder(new FullName("John", "Doe"), new Address("St. Albergue", "Paris", 99));
   }

   public void placeOrder(FullName fullName, Address address) {
      System.out.println("Some Logic");
      System.out.println("Shipping to " + address.getCity() + " on St. " + address.getStreetName() + " " + address.getStreetNumber());

   }
}
//class CreateOrderCommand { // poate fi folosit DOAR la functia de mai sus
// 100% safe refactring:
// rename: private functions, local var, param (!nu campuri)
// extract method

//class Client { // prea generica. o sa sfarseasca foarte mare
//class Person
//class Name {// si un SRL are nume dar nu first
//class ClientName { // prea restrictiv

//@Data
class FullName {
   // 20 campuri
   private final String firstName;
   private final String lastName;

   public FullName(String firstName, String lastName) {
      if (firstName == null || lastName == null) throw new IllegalArgumentException();
      this.firstName = firstName;
      this.lastName = lastName;
   }

   public String getLastName() {
      return lastName;
   }

   @Override
   public String toString() {
      return "FullName{" +
             "firstName='" + firstName + '\'' +
             ", lastName='" + lastName + '\'' +
             '}';
   }

   public String asEnterpriseName() {
      return firstName + " " + lastName.toUpperCase();
   }
}

class AnotherClass {
   public void otherMethod(FullName fullName, int x) {
      System.out.println("Another distant Logic " + x);
      System.out.println("Person: " + fullName.getLastName());
   }
}


// Entite / Document
class Person {
   private Long id;
   private FullName fullName;
   private String phone;

   public Person(FullName fullName) {
      this.fullName = fullName;

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
      System.out.println(person.getFullName().asEnterpriseName());
   }

   public void p(Address address) {
      System.out.println("Living in " + address.getCity() + " on St. " + address.getStreetName() + " " + address.getStreetNumber());
   }
}