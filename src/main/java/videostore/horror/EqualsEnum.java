package videostore.horror;

public class EqualsEnum {
   public static void main(String[] args) {

      E e = E.A;


      m(null);
   }

   private static void m(E e) {
      System.out.println(e == E.A);
//      System.out.println(E.A.equals(e));
   }
}



enum E {
   A,B
}