package victor.training.cleancode;

public class ManyParamsVO {
   public static void main(String[] args) {
      PersonName personName = new PersonName("John", "Doe");
      new ManyParamsVO().placeOrder(personName, new Address("St. Albergue", "Paris", 99));
   }

   public void placeOrder(PersonName personName, Address address) {

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
      if (firstName == null || lastName == null) throw new IllegalArgumentException();
      this.firstName = firstName;
      this.lastName = lastName;
   }

   public String getLastName() {
      return lastName;
   }


   public String getFullName() {
      return firstName + " " + lastName.toUpperCase();
   }

   public PersonName withLastName(String newLastName) {
      return new PersonName(firstName, newLastName);
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
   public void otherMethod(PersonName personName, int x) {
      System.out.println("Another distant Logic");
   }
}

// Holy entity. SACRED GROUNDS OF PERSISTENT OBJECTS.
// toata lumea lucreaza cu astea.
class Person {
   private Long id;
   private PersonName name;
   private String phone;

   public Person(String firstName, String lastName) {
      name = new PersonName(firstName, lastName);
      // TODO think: is this sufficient enforcing ?
   }

   // TODO hard-core: implement setter
   public void marita(Person ala) {
      name = name.withLastName(ala.getName().getLastName());
   }


   public PersonName getName() {
      return name;
   }

}

class PersonService {
   public void f(Person person) {
      String fullNameStr = person.getName().getFullName();
      System.out.println(fullNameStr);
   }
   {
      Address address = new Address("Bucuresti", "Mea", 99);
      p(address);
   }

   public void p(Address address) {
      System.out.println("Living in " + address.getCity() + " on St. " + address.getStreetName() + " " + address.getStreetNumber());
   }
}