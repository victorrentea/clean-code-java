package victor.training.refactoring;

class ParameterizeAndExtract {

   public void f() {
      System.out.println("Logic F");

      f(4);
   }

   public void g() {
      System.out.println("Logic G");
      f(3);
   }

   private void f(int n) {
      for (int i = 0; i < n; i++) {
         System.out.println("Code care face ALBA" + i);
      }
   }

}