package victor.training.fp;

public class OptionalAsArg {

   public void method() {
      bizLogic("a",1L);
      ifSmth("a");
   }

   public void bizLogic(String s, Long id) {
      System.out.println("Logic with " + s);
      System.out.println("Logic with " + id);

   }

   public void ifSmth(String s) {
//         stuff
   }
}
