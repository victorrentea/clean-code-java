package victor.training.cleancode;

public class ManyParamsVO {
   public static void main(String[] args) {
      new ManyParamsVO().placeOrder(new PersonName("John", "Doe"), new Address("St. Albergue", "Paris", 99));
   }

   public void placeOrder(PersonName personName, Address address) {
      if (personName.getFirstName() == null || personName.getLastName() == null) throw new IllegalArgumentException(); // TODO push in PersonName constructor

      System.out.println("Some Logic");
      System.out.println("Shipping to " + address.getCity() + " on St. " + address.getStreetName() + " " + address.getStreetNumber());

   }
}


class AnotherClass {
   public void otherMethod(PersonName personName, int x) {
      if (personName.getFirstName() == null || personName.getLastName() == null) throw new IllegalArgumentException();

      System.out.println("Another distant Logic " + x);
      System.out.println("Person: " + personName.getLastName());
   }
}

class Person {
   private Long id;
   private String firstName;
   private String lastName;
//   private PersonName name;// TODO
   private String phone;

   public Person(String firstName, String lastName) {
      this.firstName = firstName;
      this.lastName = lastName;
      if (firstName == null || lastName == null) throw new IllegalArgumentException();
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

   public void p(Address address) {
      System.out.println("Living in " + address.getCity() + " on St. " + address.getStreetName() + " " + address.getStreetNumber());
   }
}