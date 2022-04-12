package victor.training.cleancode.immutables.tracing;

public class ImmutableTracing {
   static class Data {
      private final int x;

      public Data(int x) {
         this.x = x;
      }

      public int getX() {
         return x;
      }

      public Data withX(int newX) {
         return new Data(newX);
      }
   }

   public static void main(String[] args) {
      h();
   }

   public static void h() {
      Data data = new Data(1);
      // more code
      g(data);
   }

   public static void g(Data data) {
      data = data.withX(2);
      // more code
      data = evil(data);
      // more code
      f(data);
   }

   public static void f(Data data) {
      if (data.getX() == 1) {
         System.out.println("Launch missile");
      }
   }

   private static Data evil(Data data) {
      return deeperEvil(data);
   }

   private static Data deeperEvil(Data data) {
      return data.withX(3);
   }



}

