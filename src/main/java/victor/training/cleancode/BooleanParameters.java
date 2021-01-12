package victor.training.cleancode;

import java.util.List;
import java.util.stream.Collectors;

public class BooleanParameters {
   public static void main(String[] args) {
      // The big method is called from various foreign places in the codebase
      bigUglyMethod(1, 5);
      bigUglyMethod(2, 4);
      bigUglyMethod(3, 3);
      bigUglyMethod(4, 2);
      bigUglyMethod(5, 1);

      // TODO From my use-case #323, I call it too, to do more within:
      BooleanParameters obj = new BooleanParameters();
//      obj.setCr324(true);
      obj.bigUglyMethod323(2, 1);
   }

   private boolean cr324;

   public void setCr324(boolean cr324) {
      this.cr324 = cr324;
   }

   void bigUglyMethod323(int b, int a) {
      beforeLogic(b, a);

      if (cr324) {
         System.out.println("Ceva doar pentru mine, ca ne stim");
      }
      System.out.println("Doar pentru CR323 : #sieu  " + b);
      afterLogic(b);
   }

   static void bigUglyMethod(int b, int a) {
      beforeLogic(b, a);
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

   public void bossLevelStuffFluff(List<Task> tasks) {
      bossStart();
      startTasks(tasks);
      printRunning(tasks);
      bossEnd(tasks);
   }

   public void bossLevelStuffFluff323(List<Task> tasks, boolean cr323) {
      bossStart();
      startTasks(tasks);
      for (Task task : tasks) {
         System.out.println("My Logic: " + task);
      }
      printRunning(tasks);
      bossEnd(tasks);
   }

   private void bossEnd(List<Task> tasks) {
      System.out.println("Logic6 " + tasks.size());
      System.out.println("Task Ids: " + tasks.stream().map(Task::getId).collect(Collectors.toList()));
      System.out.println("Logic7");
   }

   private void printRunning(List<Task> tasks) {
      int index = 0;
      for (Task task : tasks) {
         index++;
         System.out.println("Logic5 " + index + " on " + task.isRunning());
      }
   }

   private void startTasks(List<Task> tasks) {
      for (Task task : tasks) {
         System.out.println("Logic4: Validate " + task);
         task.start();
      }
   }

   private void bossStart() {
      System.out.println("Logic1");
      System.out.println("Logic2");
      System.out.println("Logic3");
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