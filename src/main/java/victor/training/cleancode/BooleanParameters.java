package victor.training.cleancode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

   static void bigUglyMethod(int b, int a) { // 521
      beforeLogic(b, a);
      afterLogic(b);
   }
   static void bigUglyMethod323(int b, int a) { // 521
      beforeLogic(b, a);
      System.out.println("LOGIC Just for me : ");
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

   public void bossLevelStuffFluff323(List<Task> tasks) {
      List<Long> taskIds = bossBefore(tasks);
      for (Task task : tasks) {
         // TODO When **I** call this method, I want this to run HERE, too:
         System.out.println("My Logic: " + task);
      }
      bossAfter(tasks, taskIds);
   }

   public void bossLevelStuffFluff(List<Task> tasks) {
      List<Long> taskIds = bossBefore(tasks);
      bossAfter(tasks, taskIds);
   }

   private void bossAfter(List<Task> tasks, List<Long> taskIds) {
      for (int index = 0, tasksSize = tasks.size(); index < tasksSize; index++) {
         Task task = tasks.get(index);
         System.out.println("Logic5 " + index + " on " + task.isRunning());
      }
      bossFooter(tasks, taskIds);
   }

   private List<Long> bossBefore(List<Task> tasks) {
      bossHeader();

      for (Task task : tasks) {
         task.start();
      }

      //const taskIds = someArr.map(task => task.id);
      return tasks.stream().map(Task::getId).collect(toList());
   }

   private void bossFooter(List<Task> tasks, List<Long> taskIds) {
      System.out.println("Logic6 " + tasks.size());
      System.out.println("Task Ids: " + taskIds);
      System.out.println("Logic7");
   }

   private void bossHeader() {
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