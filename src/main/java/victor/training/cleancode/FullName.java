package victor.training.cleancode;

import lombok.With;

import java.util.Objects;
import java.util.Optional;

public class FullName {
   private final String firstName;
   @With
   private final String middleName;
   private final String lastName;

   public FullName(String firstName, String middleName, String lastName) {
      this.firstName = Objects.requireNonNull(firstName);
      this.middleName = middleName;
      this.lastName = Objects.requireNonNull(lastName);
   }

   public FullName(String firstName, String lastName) {
      this(firstName, null, lastName);
   }

   public String getFirstName() {
      return firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public Optional<String> getMiddleName() {
      return Optional.ofNullable(middleName);
   }

   public FullName withMiddleName(String middleName) {
      return new FullName(firstName, middleName, lastName);
   }
}

//@Value
//public class FullName {
//   @NonNull
//   String firstName;
//   @With
//   String middleName;
//   @NonNull
//   String lastName;
//
//}
