package victor.training.cleancode;

import java.util.List;

public class BooleanParameters {
   public static void main(String[] args) {
      // The method is called from various foreign places in the codebase
      bigUglyMethod(1, 5);
      bigUglyMethod(2, 4);
      bigUglyMethod(3, 3);
      bigUglyMethod(4, 2);
      bigUglyMethod(5, 1);

      // TODO From my use-case, I call it too, to do more within:
      bigUglyMethod323(2, 1);
      // TODO From my use-case 325, I call it too, to do more within:
      bigUglyMethod325(2, 1);

   }

   static void bigUglyMethod325(int b, int a) {
      System.out.println("PT 325");
      bigUglyMethod(b, a);
   }
   static void bigUglyMethod(int b, int a) {
      beforeLogic(b, a);

      afterLogic(b);
   }


   static void bigUglyMethod323(int b, int a) {
      beforeLogic(b, a);

      System.out.println("Aici vreau sa fac si eu ceva cu " + a + " si " + b + " dar doar cand eu chem metoda asta");

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

   public void bossLevelStuffFluff(List<Integer> tasks) {
      beforeBoss(tasks);


      afterBoxx(tasks);
   }
   public void bossLevelStuffFluff324(List<Integer> tasks) {
      beforeBoss(tasks);

      for (int task : tasks) {
         // TODO When **I** call this method, I want this to run HERE, too:
         System.out.println("My Logic: " + task);
      }

      afterBoxx(tasks);
   }

   private void afterBoxx(List<Integer> tasks) {
      int i = 0;
      for (int task : tasks) {
         i++;
         System.out.println("Logic5 " + i + " on " + task);
      }
      System.out.println("Logic6 " + tasks.size());
      System.out.println("Logic7");
   }

   private void beforeBoss(List<Integer> tasks) {
      System.out.println("Logic1");
      System.out.println("Logic2");
      System.out.println("Logic3");
      for (int task : tasks) {
         System.out.println("Logic4: Validate " + task);
         // 1: acest pas MODIFICA Task - nu e o prob dc modificarile implica doar instanta curenta
         // 2: pasul asta reomteSystem.authenticat(task.user);
      }
   }

   public void bossLevelStuffNoFluff(List<Integer> tasks) {
      System.out.println("Logic1");
      System.out.println("Logic2");
      System.out.println("Logic7 " + tasks);
      System.out.println("Logic7");
   }
   public void bossLevelNoStuff() {
      System.out.println("Logic1");
      System.out.println("Logic7");
   }

}


class Task {

}