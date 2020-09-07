package victor.training.cleancode;

import java.util.List;

public class BooleanParameters {
   public static void main(String[] args) {
      // The method is called from various foreign places in the codebase
      bigUglyMethod(1, 5, false);
      bigUglyMethod(2, 4, false);
      bigUglyMethod(3, 3, false);
      bigUglyMethod(4, 2, false);
      bigUglyMethod(5, 1, false);

      // TODO From my use-case CR323, I call it too, to do more within:
      bigUglyMethod(2, 1, true);

   }

   static void bigUglyMethod(int b, int a, boolean cr323) {
      System.out.println("Complex Logic 1 " + a + " and " + b);
      System.out.println("Complex Logic 2 " + a);
      System.out.println("Complex Logic 3 " + a);

      // X aici
      if (cr323) {
         System.out.println("Si asta " + a + b);
      }

      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
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