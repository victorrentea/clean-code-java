package victor.training.pure;

import lombok.With;

public class ImmutableTracing {

   public static void main(String[] args) {
      new MutableHell().h();
   }
}


class MutableHell {
   static class Mutable {
      @With
      private final int x;
      @With
      private final int y;
      private final int z;

      Mutable(int x, int y, int z) {
         this.x = x;
         this.y = y;
         this.z = z;
      }

      public int getX() {
         return x;
      }

      public int getY() {
         return y;
      }

      public int getZ() {
         return z;
      }

//      public Mutable withX(int x) {
//         return new Mutable(x, getY(), getZ());
//      }
   }

   public void h() {
      Mutable data = new Mutable(1, 10, 20);
      // more code
      g(data);
   }

   public void g(Mutable data) {
      data = data.withX(2);
      // more code
//      data = innocent(data); // teoretic in innocent se poate modifica orice din obietul Data.

      XY xy = innocent(data);
      data = data.withX(xy.getX()).withY(xy.getY());
//      data = data.withXY(xy);
      // more code
      f(data);
   }

   private XY innocent(Mutable data) {
      return new XY(evil(data), 77);
   }

   private int evil(Mutable data) {
      return deeperEvil(data);
   }

   private int deeperEvil(Mutable data) {
      return 3;
   }

   public void f(Mutable data) {
      if (data.getX() == 1) {
         System.out.println("Launch missile");
      }
   }

}