package victor.training.cleancode;

public class FullName {
   private final String firstName;
   private final String lastName;

   public FullName(String firstName, String lastName) {
      if (firstName == null || lastName == null) {
         throw new IllegalArgumentException();
      }
      this.firstName = firstName;
      this.lastName = lastName;
   }

   public String getFirstName() {
      return firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public String toEnterpriseName() {
      return firstName + " " + lastName.toUpperCase();
   }

   public FullName withLastName(String newLastName) {
      return new FullName(firstName, newLastName);
   }
}
