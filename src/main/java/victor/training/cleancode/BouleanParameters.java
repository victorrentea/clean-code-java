package victor.training.cleancode;

import java.util.Iterator;
import java.util.List;

public class BouleanParameters {
   public static void main(String[] args) {
      // The big method is called from various foreign places in the codebase
      bigUglyMethod(1, 5);
      bigUglyMethod(2, 4);
      bigUglyMethod(3, 3);
      bigUglyMethod(4, 2);
      bigUglyMethod(5, 1);

      // TODO From my use-case #323, I call it too, to do more within:
      bigUglyMethodCR323(2, 1);

   }

   static void bigUglyMethod(int b, int a) {
      beforeLogic(b, a);
      afterLogic(b);
   }

   static void bigUglyMethodCR323(int b, int a) {
      beforeLogic(b, a);
      System.out.println("Doar pentru CR323 " + a);
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

   public void bossLevelStuffFluffCr323(List<Task> tasks) {
      beforeLogic(tasks);
      intermediateLogicCr323(tasks);
      afterLogic(tasks);
   }

   private void intermediateLogicCr323(List<Task> tasks) {
      for (Task task : tasks) {
         // TODO When **I** call this method, I want this to run HERE, too:
         System.out.println("My Logic: " + task);
      }
   }

   public void bossLevelStuffFluff(List<Task> tasks) {
      beforeLogic(tasks);
      afterLogic(tasks);
   }

   private void afterLogic(List<Task> tasks) {
      int index = 0;
      for (Task task : tasks) {
         index++;
         System.out.println("Logic5 " + index + " on " + task.isRunning());
      }
      System.out.println("Logic6 " + tasks.size());
      System.out.println("Logic7");
   }

   private void beforeLogic(List<Task> tasks) {
      System.out.println("Logic1");
      System.out.println("Logic2");
      System.out.println("Logic3");
      //      long id = 0; // daca modifici stare in afara for-ului pe care apoi o citesti, faci buguri cand spargi forul.
      for (Task task : tasks) {
         System.out.println("Logic4: remote system. authenticate(task.getUser) " + task);
         task.start();
//          id = task.getId();

      }
   }


   public void bossLevelStuffNoFluff(List<Task> tasks) {
      System.out.println("Logic1");
      System.out.println("Logic2");
      System.out.println("Logic7 " + tasks);
      System.out.println("Logic7");
   }


   public void bossLevelNoStuff(List<Task> tasks) {
      System.out.println("Logic1");
      System.out.println("Logic7");
   }

}


class Task {
   private boolean running;
   private Long id;

   public Long getId() {
      return id;
   }

   public void start() {
      running = true;
   }

   public boolean isRunning() {
      return running;
   }
}