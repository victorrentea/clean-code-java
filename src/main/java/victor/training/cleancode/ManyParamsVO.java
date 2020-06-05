package victor.training.cleancode;

public class ManyParamsVO {
   public static void main(String[] args) {
      new ManyParamsVO().placeOrder(new PersonName("John", "Doe"), "St. Albergue", "Paris", 99);
   }

   public void placeOrder(PersonName personName, String city, String streetName, Integer streetNumber) {
      if (personName.getFirstName() == null || personName.getLastName() == null) throw new IllegalArgumentException();

      System.out.println("Some Logic");
   }
}
//class ClientName { // prea specifi. nu reutilizabil
// Person poate contine
// Name e gresit - si un SRL are nume
// PersonName
class PersonName {
   private final String firstName;
   private final String lastName;

   public PersonName(String firstName, String lastName) {
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

//class OrderDetails { // prea specific. nu poate fi refolosit
//class Client // prea vag. in client poti sa pui orice, si tu si colegu/a
//class Address {
//   String city;
//   String streetName;
//   Integer streetNumber;
//}


class AnotherClass {
   public void otherMethod(String firstName, String lastName, int x) {
      if (firstName == null || lastName == null) throw new IllegalArgumentException();

      System.out.println("Another distant Logic");
   }
}

class Person {
   private Long id;
   private String firstName;
   private String lastName;
   private String phone;

   public Person(String firstName, String lastName) {
      this.firstName = firstName;
      this.lastName = lastName;
      if (firstName == null || lastName == null) throw new IllegalArgumentException();
      // TODO think: is this sufficient enforcing ?
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   // TODO hard-core: implement setter
   public void setLastName(String lastName) {
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
      String fullNameStr = person.getFirstName() + " " + person.getLastName().toUpperCase();
      System.out.println(fullNameStr);
   }

   public void p(String city, String streetName, Integer streetNumber) {
      System.out.println("Living in " + city + " on St. " + streetName + " " + streetNumber);
   }
}