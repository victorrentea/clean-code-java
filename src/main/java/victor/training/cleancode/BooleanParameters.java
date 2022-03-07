package victor.training.cleancode;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

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
   public void bossLevel(boolean fluff, List<Task> tasks, boolean cr323) {
      int index = 0; // TODO ALT-ENTER > move closer to usages
      int j = tasks.size();
      System.out.println("Logic1");
      List<Integer> taskIds = new ArrayList<>();
      System.out.println("Logic2");
      if (fluff) {
         System.out.println("Logic3");
         for (Task task : tasks) {
            System.out.println("Logic4: Validate " + task);
            task.setRunning();
         }
         for (Task task : tasks) {
            taskIds.add(task.getId());
         }
         for (Task task : tasks) {
            // TODO When **I** call this method, I want this to run HERE, too:
            if (cr323) {
               System.out.println("My Logic: " + task);
            }
         }
         for (Task task : tasks) {
            index++;
            System.out.println("Logic5 " + index + " on " + task.isRunning());
         }
         System.out.println("Logic6 " + j);
         System.out.println("Task Ids: " + taskIds);
      } else {
         System.out.println("Logic7 " + tasks);
      }
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