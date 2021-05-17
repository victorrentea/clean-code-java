package victor.training.cleancode;

import java.util.ArrayList;
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
      beforeLogic(b, a);
      afterLogic(b);
   }
   static void bigUglyMethod323(int b, int a) {
      beforeLogic(b, a);
      System.out.println("This needs to happen also when I call the method");
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

   public void bossLevelStuffFluff(List<Task> tasks) {
      List<Long> taskIds = getTaskIds(tasks);
      bossEnd(tasks, taskIds);
   }
   public void bossLevelStuffFluff323(List<Task> tasks) {
      List<Long> taskIds = getTaskIds(tasks);

      for (Task task : tasks) {
         System.out.println("My Logic for cr323: " + task);
      }

      bossEnd(tasks, taskIds);
   }

   private void bossEnd(List<Task> tasks, List<Long> taskIds) {
      int index = 0;
      for (Task task : tasks) {
         index++;
         System.out.println("Logic5 " + index + " on " + task.isRunning());
      }
      System.out.println("Logic6 " + tasks.size());
      System.out.println("Task Ids: " + taskIds);
      System.out.println("Logic7");
   }

   private List<Long> getTaskIds(List<Task> tasks) {
      System.out.println("Logic1");
      System.out.println("Logic2");
      System.out.println("Logic3");

      for (Task task : tasks) {
         task.start();
      }

      return tasks.stream().map(Task::getId).collect(toList());
   }

   private void unknownMethod(List<Task> tasks) {

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