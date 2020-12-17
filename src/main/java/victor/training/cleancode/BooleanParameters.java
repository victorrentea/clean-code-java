package victor.training.cleancode;

import java.util.ArrayList;
import java.util.List;

public class BooleanParameters {
   public static void main(String[] args) {
      // The big method is called from various foreign places in the codebase
      bigUglyMethod(1, 5);
      bigUglyMethod(2, 4);
      bigUglyMethod(3, 3);
      bigUglyMethod(4, 2);
      bigUglyMethod(5, 1);

      // TODO From my use-case #323, I call it too, to do more within:
      bigUglyMethod323(2, 1);

   }

   static void bigUglyMethod(int b, int a) {
      before(b, a);
      after(b);
   }
   static void bigUglyMethod323(int b, int a) {
      before(b, a);
      System.out.println("MY EXTRA STUFF THAT SHOULD RUN ONLY WHEN **I** CALL THIS FUNCTION");
      after(b);
   }

   private static void before(int b, int a) {
      System.out.println("Complex Logic 1 " + a + " and " + b);
      System.out.println("Complex Logic 2 " + a);
      System.out.println("Complex Logic 3 " + a);
   }

   private static void after(int b) {
      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
   }


   // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================

   public void bossLevelStuffFluff323(List<Task> tasks) {
      List<Long> taskIds = beforeBoss(tasks);

      for (Task task : tasks) {
         System.out.println("My Logic: " + task);
      }

      afterBoss(tasks, taskIds);
   }
   public void bossLevelStuffFluff(List<Task> tasks) {
      List<Long> taskIds = beforeBoss(tasks);

      afterBoss(tasks, taskIds);
   }

   private void afterBoss(List<Task> tasks, List<Long> taskIds) {
      int index = 0;
      for (Task task : tasks) {
         index++;
         System.out.println("Logic5 " + index + " on " + task.isRunning());
      }
      System.out.println("Logic6 " + tasks.size());
      System.out.println("Task Ids: " + taskIds);
      System.out.println("Logic7");
   }

   private List<Long> beforeBoss(List<Task> tasks) {
      System.out.println("Logic1");
      System.out.println("Logic2");
      System.out.println("Logic3");
      List<Long> taskIds = new ArrayList<>();
      for (Task task : tasks) { //sizeof(tasks)
         System.out.println("Logic4: Validate " + task);
         task.start();
         taskIds.add(task.getId());
      }
      return taskIds;
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
   private Long id;
   private boolean running;

   public void start() {
      running = true;
   }

   public boolean isRunning() {
      return running;
   }

   public Long getId() {
      return id;
   }
}