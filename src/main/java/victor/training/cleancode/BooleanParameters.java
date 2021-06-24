package victor.training.cleancode;

import java.util.List;

import static java.util.stream.Collectors.toList;

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
      System.out.println("#SIEU pt cazul meu");
      after(b);
   }

   private static void after(int b) {
      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
   }

   private static void before(int b, int a) {
      System.out.println("Complex Logic 1 " + a + " and " + b);
      System.out.println("Complex Logic 2 " + a);
      System.out.println("Complex Logic 3 " + a);
   }


   // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================

   public void bossLevelStuffFluff(List<Task> tasks) { // (ObiectCuListSiBoolean param)
      List<Long> taskIds = bossBefore(tasks);
      bossAfter(tasks, taskIds);
   }

   public void bossLevelStuffFluff323(List<Task> tasks) { // (ObiectCuListSiBoolean param)
      List<Long> taskIds = bossBefore(tasks);
      for (Task task : tasks) {
         // TODO When **I** call this method, I want this to run HERE, too:
         System.out.println("My Logic: " + task);
      }
      bossAfter(tasks, taskIds);
   }

   private void bossAfter(List<Task> tasks, List<Long> taskIds) {
      int index = 0;
      for (Task task : tasks) {
         index++;
         System.out.println("Logic5 " + index + " on " + task.isRunning());
      }
      System.out.println("Logic6 " + tasks.size());
      System.out.println("Task Ids: " + taskIds);
      System.out.println("Logic7");
   }

   private List<Long> bossBefore(List<Task> tasks) {
      System.out.println("Logic1");
      System.out.println("Logic2");
      System.out.println("Logic3");
      // TODO move closer to usages
      for (Task task : tasks) {
         System.out.println("Logic4: Validate " + task);
         task.setRunning();
      }

      List<Long> taskIds = tasks.stream().map(Task::getId).collect(toList());
      return taskIds;
   }


   public void bossLevelStuffNoFluff(List<Task> tasks) {
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
   private Long id;
   private boolean running;

   public void setRunning() {
      running = true;
   }

   public boolean isRunning() {
      return running;
   }

   public Long getId() {
      return id;
   }
}