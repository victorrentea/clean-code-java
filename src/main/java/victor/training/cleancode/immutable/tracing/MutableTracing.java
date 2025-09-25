package victor.training.cleancode.immutable.tracing;

public class MutableTracing {
   static class Data {
      private int x;

     protected int getX() {
         return x;
      }

     protected void setX(int x) {
         this.x = x;
      }
   }

   public static void main(String[] args) {
      h();
   }

  private static void h() {
      Data data = new Data();
      data.setX(1);
      // more code
      g(data);
   }

  private static void g(Data data) {
      data.setX(2);
      // more code
      evil(data);
      // more code
      f(data);
   }

  private static void f(Data data) {
      if (data.getX() == 1) {
         System.out.println("Launch missile");
      }
   }

   private static void evil(Data data) {
      deeperEvil(data);
   }

   private static void deeperEvil(Data data) {
      data.setX(3);
   }



}

