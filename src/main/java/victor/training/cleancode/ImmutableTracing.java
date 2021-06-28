package victor.training.cleancode;

import lombok.With;

public class ImmutableTracing {

   public static void main(String[] args) {
      new MutableHell().h();
   }
}


class MutableHell {
   class Immutable {
      @With
      private final int x;

      Immutable(int x) {
         this.x = x;
      }

      public int getX() {
         return x;
      }

   }
//   class ExtensionByAMeanColleagueThatDoesntKnowThatExtendsIS_BAD extends Immutable {
//      private int xHack;
//
//      ExtensionByAMeanColleagueThatDoesntKnowThatExtendsIS_BAD(int x) {
//         super(x);
//      }
//
//      @Override
//      public int getX() {
//         return xHack;
//      }
//
//      public void setX(int xHack) {
//         this.xHack = xHack;
//      }
//   }


   public void h() {
      Immutable data = new Immutable(1);
      // more code
      g(data);
   }

   public void g(Immutable data) {
      data = data.withX(2);
      // more code
      data = m1(data);
      m2(data);
      data = m1(data);
      // more code
      f(data);
   }


   private void m2(Immutable data) {
      System.out.println(data.getX());
   }
   private Immutable m1(Immutable data) {
      return data.withX(3);
   }

   public void f(Immutable data) {
      if (data.getX() == 1) {
         System.out.println("RUNS");
      }
   }

}