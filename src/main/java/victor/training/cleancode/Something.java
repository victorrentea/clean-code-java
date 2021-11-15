package victor.training.cleancode;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Locale;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class Something {
   public static void main(String[] args) {
      //many places
      new Something().placeOrder(new FullName("John", "Doe"), new Address("St. Albergue", "Paris", 99));
      new Something().placeOrder(new FullName("John", "Doe"), new Address("St. Albergue", "Paris", 99));
      new Something().placeOrder(new FullName("John", "Doe"), new Address("St. Albergue", "Paris", 99));
      new Something().placeOrder(new FullName("John", "Doe"), new Address("St. Albergue", "Paris", 99));
      new Something().placeOrder(new FullName("John", "Doe"), new Address("St. Albergue", "Paris", 99));
      new Something().placeOrder(new FullName("John", "Doe"), new Address("St. Albergue", "Paris", 99));
   }

   public void placeOrder(FullName fullName, Address address) { // desired


      System.out.println("Some Logic");
      System.out.println("Shipping to " + address.getCity() + " on St. " + address.getStreetName() + " " + address.getStreetNumber());
   }
}

class FullNamePlay2 {

   private record X(int i) {}
   public static void main(String[] args) {
      FullName fullName = new FullName("f", "l");
      System.out.println(fullName);
      System.out.println(fullName.firstName());
   }
}
interface Name {
   String asHumanReadable();
}


// practical use of Records :
// Value Objects to decompose your domain data 2-4 fielrs
// RX instead of Tuple3<String,String,List<Integer>> :)  --> record(String name, String address, List<Integer> orderIds) {}

record SomeSuper(int oups) {
}
//class X extends SomeSuper {}
record FullName(String firstName, String lastName)implements Name {
   FullName {
      requireNonNull(firstName, "firstName");
      requireNonNull(lastName, "lastName");
   }


   @Override
   public String asHumanReadable() {
      return firstName + " " + lastName.toUpperCase();
   }
//   private String middleName;

//   public Optional<String> firstName() {
//      return firstName.map(String::toUpperCase);
//   }
}

class Address {
   private final String city;
   private final String streetName;
   private final Integer streetNumber;

   Address(String city, String streetName, Integer streetNumber) {
      this.city = city;
      this.streetName = streetName;
      this.streetNumber = streetNumber;
   }

   public String getCity() {
      return city;
   }

   public String getStreetName() {
      return streetName;
   }

   public Integer getStreetNumber() {
      return streetNumber;
   }
}

// some other code
class AnotherClass {
   public void otherMethod(FullName fullName, int x) {

      System.out.println("Another distant Logic " + x);
      System.out.println("Person: " + fullName.lastName());
   }
}

class Person {
   private Long id;
   private String firstName;
   private String lastName;
   private String phone;

   public Person(String firstName, String lastName) {
      if (firstName == null || lastName == null)
         throw new IllegalArgumentException();
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

      // pure function: also typically very fast since they do no network.
      // 1 NO side effects, eg>
      // a) File read/write, DB, HTTP call (any network);
      // b) modifying some state (eg fields in Person class)
      // 2 Same output for same inputs:: eg DONT:
      // a) if the return of formatFullName depends on some state besides the Person
      // param
      //
      // b) current time/random
      String fullName = formatFullName(person);
      System.out.println("Record for " + fullName);
   }

   int n; // state outside of the params involved in the result

   private String formatFullName(Person person) {
      // prefix = run("SELECT aaa FROM ..."); // NOT pure because a COMMIT might occur
      // in between 2 calls of tihs function

      // anotherImpureFunction=() BAD
      //
      return /* n + *//* prefix + */person.getFirstName() + " " + person.getLastName().toUpperCase();
   }

   public void p(String streetName, String city, Integer streetNumber) {
      System.out.println("Living in " + city + " on St. " + streetName + " " + streetNumber);
   }

   public void pcaller() {
      p("Dristor", "Bucharest", 91);
   }
}