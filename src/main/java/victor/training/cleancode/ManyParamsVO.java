package victor.training.cleancode;

import javax.persistence.Entity;

public class ManyParamsVO {
   public static void main(String[] args) {
      new ManyParamsVO().placeOrder(new FullName("John", "Doe"), new Address("St. Albergue", "Paris", 99));
   }

   public void placeOrder(FullName fullName, Address address) {
      System.out.println("Some Logic");
      System.out.println("Shipping to " + address.getCity() + " on St. " + address.getStreetName() + " " + address.getStreetNumber());

   }
}

// PersonData - nu zice nimic
// PersonInfo - nu zice nimic
// PersonBAISJSAIDHIA
// PersonDetails OK

// Person{fn, ln, address, ssn, telefon, tiwtterhandle, fb acocunt, } prea vag numele. Poti pune orice in asa clasa

// Address (c,sname, snumber)
// PersonName {fn, ln} OK
// FullName {fn, ln} OK

// Folk
// Customer




class AnotherClass {
   public void otherMethod(FullName fullName, int x) {
      System.out.println("Another distant Logic " + x);
      System.out.println("Person: " + fullName.getLastName());
   }
}

@Entity
class Person {
   private Long id;
   private FullName fullName;
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
      System.out.println(person.getFullName().toEnterpriseName());
   }

   public void p(String city, String streetName, Integer streetNumber) {
      System.out.println("Living in " + city + " on St. " + streetName + " " + streetNumber);
   }
}