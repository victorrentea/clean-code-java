package victor.training.cleancode;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import java.util.Objects;

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
}
