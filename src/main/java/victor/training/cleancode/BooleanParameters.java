package victor.training.cleancode;

import com.google.common.collect.ImmutableList;
import lombok.ToString;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Implement the variation required for CR323 without adding a boolean parameter
 */
public class BooleanParameters {

   public static void main(String[] args) {
      // The big method is called from various foreign places in the codebase
      bigUglyMethod(1, new Task(5));
      bigUglyMethod(2, new Task(4));
      bigUglyMethod(3, new Task(3));
      bigUglyMethod(4, new Task(2));
      bigUglyMethod(5, new Task(1));

      // TODO From my use-case #323, I call it too, to do more within:. + task.setDetail(15)
      Task task = new Task(1);
      bigUglyMethod323(2, task);

   }

   static void bigUglyMethod(int b, Task task) {
      beforeBig(b, task);
      afterBig(b);
   }

   static void bigUglyMethod323(int b, Task task) {
      beforeBig(b, task);
      System.out.println("Logic just for CR323 : " + task);
      afterBig(b);
   }

   private static void afterBig(int b) {
      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
   }

   private static void beforeBig(int b, Task task) {
      System.out.println("Complex Logic 1 " + task + " and " + b);
      System.out.println("Complex Logic 2 " + task);
      System.out.println("Complex Logic 3 " + task);
   }


   // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================

   // see the tests
//   {
//      bossLevel(false, Collections.emptyList(), t-> {});
//      bossLevel(false, Collections.emptyList(), task-> System.out.println("My Logic: " + task));
//   }
   public void bossLevelFluff(ImmutableList<Task> tasks) {
      bossStart(tasks);
      logic5(tasks);
      bossEnd(tasks);
   }
   public void bossLevelFluff323(ImmutableList<Task> tasks) {
      bossStart(tasks);
      for (Task task : tasks) {
         System.out.println("My Logic: " + task);
      }
      logic5(tasks);
      bossEnd(tasks);
   }

   private void bossEnd(ImmutableList<Task> tasks) {
      System.out.println("Logic6 " + tasks.size());
      List<Integer> taskIds = tasks.stream().map(Task::getId).collect(toList());
      System.out.println("Task Ids: " + taskIds);
      System.out.println("Logic7");
   }

   private void logic5(ImmutableList<Task> tasks) {
      for (int i = 0; i < tasks.size(); i++) {
         Task task = tasks.get(i);
         System.out.println("Logic5 " + (i + 1) + " on " + task.isRunning());
      }
   }

   private void bossStart(ImmutableList<Task> tasks) {
      System.out.println("Logic1");
      System.out.println("Logic2");
      System.out.println("Logic3");

      for (Task task : tasks) {
         System.out.println("Logic4: Validate " + task);
         task.setRunning();
      }
   }


   public void bossLevelNoFluff(ImmutableList<Task> tasks) {
      System.out.println("Logic1");
      System.out.println("Logic2");
      System.out.println("Logic7 " + tasks);
      System.out.println("Logic7");
   }

   // private int forBody(int index, List<Integer> taskIds, Task task, boolean cr323) {}
}

@ToString
class Task {
   private final int id;
   private boolean running;

   Task(int id) {
      this.id = id;
   }

   public void setRunning() {
      running = true;
   }

   public boolean isRunning() {
      return running;
   }

   public int getId() {
      return id;
   }
}