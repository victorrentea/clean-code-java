package victor.training.cleancode;

public class FullName {
   private final String fName;
   private final String lName;

   public FullName(String fName, String lName) {
      this.fName = fName;
      this.lName = lName;
   }

   public String getfName() {
      return fName;
   }

   public String getlName() {
      return lName;
   }
}
