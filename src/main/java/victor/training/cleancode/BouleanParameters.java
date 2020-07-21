package victor.training.cleancode;

import java.util.List;
import java.util.function.Consumer;

public class BouleanParameters {
   public static void main(String[] args) {
      // The method is called from various foreign places in the codebase
      bigUglyMethod(1, 5);
      bigUglyMethod(2, 4);
      bigUglyMethod(3, 3);
      bigUglyMethod(4, 2);
      bigUglyMethod(5, 1);

      // TODO From my use-case, I call it too, to do more within:
      bigUglyMethod323(2, 1);

   }

   static void bigUglyMethod(int b, int a) {
      beforeLogic(b, a);
      afterLogic(b);
   }

   static void bigUglyMethod323(int b, int a) {
      beforeLogic(b, a);
      System.out.println("Cou meu "  + a);
      afterLogic(b);
   }

   private static void afterLogic(int b) {
      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
   }

   private static void beforeLogic(int b, int a) {
      System.out.println("Complex Logic 1 " + a + " and " + b);
      System.out.println("Complex Logic 2 " + a);
      System.out.println("Complex Logic 3 " + a);
   }


   // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================

   public void bossLevelStuffFluff(List<Integer> tasks, Consumer<Integer> f) {
      System.out.println("Logic1");
      System.out.println("Logic2");
      System.out.println("Logic3");

      for (int task : tasks) {
         System.out.println("Logic4: Validate " + task);
      }
      for (int task : tasks) {
         // TODO When **I** call this method, I want this to run HERE, too:
         // System.out.println("My Logic: " + task);
      }
      int i = 0;
      for (int task : tasks) {
         i++;
         System.out.println("Logic5 " + i + " on " + task);
      }

      System.out.println("Logic6 " + tasks.size());
      System.out.println("Logic7");
   }
   public void bossLevelStuffNoFluff(List<Integer> tasks) {
      System.out.println("Logic1");
      System.out.println("Logic2");
      System.out.println("Logic7 " + tasks);
      System.out.println("Logic7");
   }
   public void bossLevelNoStuff(List<Integer> tasks) {
      System.out.println("Logic1");
      System.out.println("Logic7");
   }

}


class Task {

}