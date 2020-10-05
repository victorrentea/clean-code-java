package victor.training.refactoring;

import java.util.List;

public class BooleanParameters {
   public static void main(String[] args) {
      // The method is called from various foreign places in the codebase
      bigUglyMethod(5, 1);
      bigUglyMethod(4, 2);
      bigUglyMethod(3, 3);
      bigUglyMethod(2, 4);
      bigUglyMethod(1, 5);

      // TODO From my use-case #323, I call it too, to do more within:
      bigUglyMethod323(1, 2);

   }

   static void bigUglyMethod(int a, int b) {
      beforeLogic(a, b);
      afterLogic(b);
   }
   static void bigUglyMethod323(int a, int b) {
      beforeLogic(a, b);
      System.out.println("Someting extra, just for me " + a);
      afterLogic(b);
   }

   private static void afterLogic(int b) {
      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
   }

   private static void beforeLogic(int a, int b) {
      System.out.println("Complex Logic 1 " + a + " and " + b);
      System.out.println("Complex Logic 2 " + a);
      System.out.println("Complex Logic 3 " + a);
   }


   // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================

   public void bossLevel(boolean stuff, boolean fluff, List<Integer> tasks) {
      int i = 0; // TODO move closer to usages
      int j = tasks.size();
      System.out.println("Logic1");
      if (stuff) {
         System.out.println("Logic2");
         if (fluff) {
            System.out.println("Logic3");
            for (int task : tasks) {
               i++;
               System.out.println("Logic4: Validate " + task);
               // TODO When **I** call this method, I want this to run HERE, too:
               // System.out.println("My Logic: " + task);
               System.out.println("Logic5 " + i + " on " + task);
            }
            System.out.println("Logic6 " + j);
         } else {
            System.out.println("Logic7 " + tasks);
         }
      }
      System.out.println("Logic7");
   }

}


class Task {

}