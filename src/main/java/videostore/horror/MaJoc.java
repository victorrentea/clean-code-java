package videostore.horror;

public class MaJoc {
   public static void main(String[] args) {


      Integer x = null;
      switch (x) {
         case 1:
            System.out.println("1");
            break;
//         case null:
//            System.out.println("x");break;

         default:
            throw new IllegalStateException("Unexpected value: " + x);
      }
   }
}
