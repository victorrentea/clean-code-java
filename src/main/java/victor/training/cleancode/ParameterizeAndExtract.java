package victor.training.cleancode;

class ParameterizeAndExtract {

   public void f(int n) {
      System.out.println("Logic F");
      common(n, 4, "CodeY ", 0);
   }

   public void g(int n) {
      System.out.println("Logic G");
      try {
         common(n, 3, "CodeX ", 0);
      } catch (Exception e) {
         throw new RuntimeException("Rethrow", e);
      }
   }

   private void common(int n, int i2, String s, int start) {
      for (int i = start; i < i2; i++) {
         if (n + i < 0) {
            System.out.println(s + i);
         } else {
            throw new IllegalArgumentException();
         }
      }
   }

}
