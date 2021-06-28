package victor.training.cleancode;

public class FullName {
   private final String fName;
   private final String lName;

   public FullName(String firstName, String lastName) {
      if (firstName == null || lastName== null) {
         throw new IllegalArgumentException();
      }

      this.fName = firstName;
      this.lName = lastName;
   }

   public String getfName() {
      return fName;
   }

   public String getlName() {
      return lName;
   }
}
