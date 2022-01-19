package victor.training.cleancode;

public record FullName(String fName, String lName) {

   public String getfName() {
      return fName;
   }

   public String getlName() {
      return lName;
   }
}
