package victor.training.refactoring;

class ParameterizeAndExtract {

   public void f(int n) {
      System.out.println("Logic F");
      omgomg(n, 4, "CodeY ");
   }


   public void g(int n) {
      System.out.println("Logic G");
      omgomg(n, 3, "CodeX ");
   }

   private void omgomg(int n, int i2, String s) {
      for (int i = 0; i < i2; i++) {
         if (n + i < 0) {
            System.out.println(s + i);
         } else {
            throw new IllegalArgumentException();
         }
      }
   }
}

class AnotherClass {

}