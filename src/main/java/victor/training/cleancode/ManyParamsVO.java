package victor.training.cleancode;

import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;

public class ManyParamsVO {
   public static void main(String[] args) {
      FullName fullName = new FullName("John", "Doe");
      new ManyParamsVO().placeOrder(fullName, "St. Albergue", "Paris", 99);
   }

   public void placeOrder(FullName fullName, String city, String streetName, Integer streetNumber) {
      System.out.println("Some Logic");
      System.out.println("Shipping to " + city + " on St. " + streetName + " " + streetNumber);

   }

}

//class Name { gresit. prea vag si prea general
class FullName {

   interface Final {}
   @NotNull(groups = Final.class)
   private final String firstName;
   @NotNull(groups = Final.class)
   private final String lastName;

   public FullName(String firstName, String lastName) {
      this(firstName, lastName, true);
   }
   public FullName(String firstName, String lastName, boolean special) {
      this.firstName = firstName;
      this.lastName = lastName;
      if (special) {
         if (firstName == null || lastName == null) throw new IllegalArgumentException();
      }
   }
   // peferi functie si nu in constr daca pe anumite use-caseuri validarea este optionala/nu exista.
   public void validate(Validator validator) {
      validator.validate(this, Final.class);
   }
   public String getFirstName() {
      return firstName;
   }

   public String getLastName() {
      return lastName;
   }

}

class AnotherClass {
   // nu e codu tau si totusi il imbunatatesti
   public void otherMethod(FullName fullName, int x) {
      System.out.println("Another distant Logic " + x);
      System.out.println("Person: " + fullName.getLastName());
   }
}

//@Entity
class Person {
   private Long id;
   @Valid
   private FullName fullName;
   private String phone;

   public Person(String firstName, String lastName) {
      this.fullName = new FullName(firstName, lastName);
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
      String fullNameStr = person.getFullName().getFirstName() + " " + person.getFullName().getLastName().toUpperCase();
      System.out.println(fullNameStr);
   }

   public void p(String city, String streetName, Integer streetNumber) {
      System.out.println("Living in " + city + " on St. " + streetName + " " + streetNumber);
   }
}