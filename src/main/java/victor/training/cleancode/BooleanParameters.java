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

      // TODO From my use-case CR323, I call it too, to do more within:
      bigUglyMethod323(2, 1);

   }

   static void bigUglyMethod(int b, int a) {
      beforeLogic(b, a);
      afterLogic(b);
   }

   static void bigUglyMethod323(int b, int a) {
      beforeLogic(b, a);
      System.out.println("Si asta " + a + b);
      afterLogic(b);
   }

   private static void beforeLogic(int b, int a) {
      System.out.println("Complex Logic 1 " + a + " and " + b);
      System.out.println("Complex Logic 2 " + a);
      System.out.println("Complex Logic 3 " + a);
   }

   private static void afterLogic(int b) {
      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
   }


   // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================


   public void bossLevelStuffFluff345(List<Integer> tasks) {
      bossBefore(tasks);
      for (int task : tasks) {
         // TODO When **I** call this method, I want this to run HERE, too:
         System.out.println("My Logic: " + task);
      }
      bossAfter(tasks);
   }
   public void bossLevelStuffFluff(List<Integer> tasks) {
      bossBefore(tasks);
      bossAfter(tasks);
   }

   private void bossAfter(List<Integer> tasks) {
      int i = 0;
      for (int task : tasks) {
         i++;
         System.out.println("Logic5 " + i + " on " + task);
      }
      System.out.println("Logic6 " + tasks.size());
      System.out.println("Logic7");
   }

   private void bossBefore(List<Integer> tasks) {
      System.out.println("Logic1");
      System.out.println("Logic2");
      System.out.println("Logic3");
      for (int task : tasks) {
         System.out.println("Logic4: Validate " + task);
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


   // Dar Norbert a vazut ca: cele 2 au outputuri diferite!!! Daca conta,==> buba
//   public void bossLevelStuffFluff(List<Integer> tasks) {
//      int i = 0;
//      for (int task : tasks) {
//         System.out.println("Logic4: Validate " + task);
//
//         // TODO When **I** call this method, I want this to run HERE, too:
//         System.out.println("My Logic: " + task);
//
//         i++;
//         System.out.println("Logic5 " + i + " on " + task);
//      }
//   }
//
//   public void bossLevelStuffFluff(List<Integer> tasks) {
//      for (int task : tasks) {
//         System.out.println("Logic4: Validate " + task);
//      }
//      for (int task : tasks) {
//         // TODO When **I** call this method, I want this to run HERE, too:
//         System.out.println("My Logic: " + task);
//      }
//      int i = 0;
//      for (int task : tasks) {
//         i++;
//         System.out.println("Logic5 " + i + " on " + task);
//      }
//   }


      //DAR : ======================
      //
      //1) set current user 1
      //2) place order for current user: O1
      //
      //3) set current user 2
      //4) place order for current user: O2
      //
      //
      //
      //Cand operatia schimba date intr-un mod stateful ==> BUM
      //1) set current user 1
      //2) set current user 2
      //3) place order for current user: O1
      //4) place order for current user: O2
      //

}


class Task {

}