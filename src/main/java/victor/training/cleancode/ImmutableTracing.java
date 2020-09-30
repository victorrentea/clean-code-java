package victor.training.cleancode;

public class ImmutableTracing {

   public static void main(String[] args) {
      new MutableHell().h();
   }
}


class MutableHell {
   class Mutable {
      private int x;

      public int getX() {
         return x;
      }

      public void setX(int x) {
         this.x = x;
      }
   }

   class HoldingReference {
      private Mutable data;

      public void setData(Mutable data) {
         this.data = data;
      }

      public void hack() {
         data.setX(1);
      }
   }

   private HoldingReference obj = new HoldingReference();

   public void h() {
      Mutable data = new Mutable();
      // more code
      obj.setData(data);
      // more code
      g(data);
   }

   public void g(Mutable data) {
      data.setX(2);
      // more code
      evil(data);
      // more code
      obj.hack();
      // more code
      f(data);
   }

   private void evil(Mutable data) {
      data.setX(3);
   }

   public void f(Mutable data) {
      if (data.getX() == 1) {
         System.out.println("RUNS");
      }
   }

}