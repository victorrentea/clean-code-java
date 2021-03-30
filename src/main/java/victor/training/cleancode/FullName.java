package victor.training.cleancode;

import javax.persistence.Embeddable;

import static java.util.Objects.requireNonNull;

@Embeddable
public class FullName {
   private String firstName;
   private String lastName;
   protected FullName() {} // for Hibernate eyes only

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

   public FullName withLastName(String newLastname) {
      return new FullName(firstName, newLastname);
   }

   public String asCorporateName() {
      return firstName + " " + lastName.toUpperCase();
   }
}
