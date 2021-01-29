package victor.training.cleancode;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class FullName {
   private final String firstName;
   private final String lName;

   public FullName(String fName, String lName) {
      this.firstName = requireNonNull(fName);
      this.lName = requireNonNull(lName);
   }

   public String getFirstName() {
      return firstName;
   }

   public String getLastName() {
      return lName;
   }

   public String asEnterpriseName() {
      return getFirstName() + " " + getLastName().toUpperCase();
   }
}
