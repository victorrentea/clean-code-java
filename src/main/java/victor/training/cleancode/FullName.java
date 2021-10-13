package victor.training.cleancode;

import java.util.Objects;

public final class FullName {
   private final String firstName;
   private final String lastName;

   public FullName(String firstName, String lastName) {
      this.firstName = firstName;
      this.lastName = lastName;
   }

   public String firstName() {
      return firstName;
   }

   public String lastName() {
      return lastName;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj == this) return true;
      if (obj == null || obj.getClass() != this.getClass()) return false;
      var that = (FullName) obj;
      return Objects.equals(this.firstName, that.firstName) &&
             Objects.equals(this.lastName, that.lastName);
   }

   @Override
   public int hashCode() {
      return Objects.hash(firstName, lastName);
   }

   @Override
   public String toString() {
      return "FullName[" +
             "firstName=" + firstName + ", " +
             "lastName=" + lastName + ']';
   }

   public String getLastName() {
      return lastName;
   }

   public String getFirstName() {
      return firstName;
   }
}