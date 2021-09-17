package victor.training.cleancode;

public class ReturnSwitchWTF {
   public int getHeight(TypeOfStuf stuf) {
      return switch (stuf.name()) {
         case "INALT" -> 250;
         case "JOS" -> 80;
         case "VAMA_VECHE" -> 0;
         default -> {throw new IllegalArgumentException("JDD");}
      };

   }

   public void test() {
      System.out.println(getHeight(TypeOfStuf.INALT));
   }
}


enum TypeOfStuf {
   INALT,
   JOS,
   VAMA_VECHE,
   COSTINESTI
}