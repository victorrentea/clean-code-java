package victor.training.cleancode;

public class ReturnSwitchWTF {
   public int getHeight(TypeOfStuf stuf) {
      return switch (stuf) {
         case INALT -> 250;
         case JOS -> 80;
         case VAMA_VECHE -> 0;
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