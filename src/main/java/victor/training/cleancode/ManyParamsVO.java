package victor.training.cleancode;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import java.util.List;
import java.util.Map;

public class ManyParamsVO {
   public static void main(String[] args) {
      FullName fullName = new FullName("John", "Doe");
      new ManyParamsVO().placeOrder(fullName, new Address("St. Albergue", "Paris", 99));
   }

   public void placeOrder(FullName fullName, Address address) {
      if (fullName.getfName() == null || fullName.getlName() == null) throw new IllegalArgumentException();

      System.out.println("Some Logic");
      System.out.println("Shipping to " + address.getCity() + " on St. " + address.getStreetName() + " " + address.getStreetNumber());

   }
}




class AnotherClass {
   public void otherMethod(FullName fullName, int x) {
      if (fullName.getfName() == null || fullName.getlName() == null) throw new IllegalArgumentException();

      System.out.println("Another distant Logic " + x);
      System.out.println("Person: " + fullName.getlName());
   }
}

class Incident {

   Map<PersonId, List<IncidentId>> map;
   PersonId personId;
}

@Embeddable
class PersonId {
   private Long id;
}
@Embeddable
class IncidentId {
   private Long id;
}

class Person {
   @EmbeddedId
   private PersonId id;
   private String firstName;
   private String lastName;
   private String phone;

   public Person(String firstName, String lastName) {
      if (firstName == null || lastName == null) throw new IllegalArgumentException();
      this.firstName = firstName;
      this.lastName = lastName;
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
      System.out.println("Hi there, " + person.getFirstName());

      String fullNameStr = person.getFirstName() + " " + person.getLastName().toUpperCase();
      System.out.println("Record for " + fullNameStr);
   }

   public void p(String streetName, String city, Integer streetNumber) {
      System.out.println("Living in " + city + " on St. " + streetName + " " + streetNumber);
   }

   public void pcaller() {
       p("Dristor", "Bucharest", 91);
   }
}