package victor.training.cleancode;

import lombok.With;

public class ImmutableTracing {

   public static void main(String[] args) {
      new MutableHell().h();
   }
}


class MutableHell {
   class Mutable {
      @With
      private final int x;

      Mutable(int x) {
         this.x = x;
      }

      public int getX() {
         return x;
      }

   }

   class HoldingReference {
      private Mutable data;

      public void setData(Mutable data) {
         this.data = data;
      }

      public void hack() {
//         data.setX(1);
      }
   }

   private HoldingReference obj = new HoldingReference();

   public void h() {
      Mutable data = new Mutable(1);
      // more code
      obj.setData(data);
      // more code
      g(data);
   }

   public void g(Mutable data) {
//      data.setX(2);
      // more code
      data = evil(data);
      // more code
      obj.hack();
      // more code
      f(data);
   }

   private Mutable evil(Mutable data) {
      return data.withX(3);
   }

   public void f(Mutable data) {
      if (data.getX() == 1) {
         System.out.println("RUNS");
      }
   }

}