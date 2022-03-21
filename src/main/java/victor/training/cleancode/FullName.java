package victor.training.cleancode;

public class FullName {
   private final String firstName;
   private final String lastName;

   public FullName(String firstName, String lName) {
      this.firstName = firstName;
      this.lastName = lName;
   }

   public String getFirstName() {
      return firstName;
   }

   public String getLastName() {
      return lastName;
   }
}
