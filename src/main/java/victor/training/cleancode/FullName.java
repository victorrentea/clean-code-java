package victor.training.cleancode;

import javax.persistence.Embeddable;

import static java.util.Objects.requireNonNull;

@Embeddable
public class FullName {
   private String firstName;
   private String lastName;

   protected FullName() {}

   public FullName(String firstName, String lastName) {
      this.firstName = requireNonNull(firstName);
      this.lastName = requireNonNull(lastName);
   }

   public String getFirstName() {
      return firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public String asEnterpriseName() {
      return firstName + " " + lastName.toUpperCase();
   }
//   public String asCsv() {
//      return firstName + ";" + lastName.toUpperCase();
//   }

   public FullName withLastName(String newLastName) {
      return new FullName(firstName, newLastName);
   }
}
