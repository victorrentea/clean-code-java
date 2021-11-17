package victor.training.cleancode;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Implement the variation required for CR323 without adding a boolean parameter
 */

public class BooleanParameters {

   public static void main(String[] args) {
      // The big method is called from various foreign places in the codebase that I don't know of
      bigUglyMethod(1, new Task(5), false);
      bigUglyMethod(2, new Task(4), false);
      bigUglyMethod(3, new Task(3), false);
      bigUglyMethod(4, new Task(2), false);
      bigUglyMethod(5, new Task(1), false);

      // TODO From my use-case #323, I call it too, to do more within:. + task.setDetail(15)
      Task task = new Task(1);
      bigUglyMethod(2, task, true);

   }

   static void bigUglyMethod(int b, Task task, boolean cr323) {
      System.out.println("Complex Logic 1 " + task + " and " + b);
      System.out.println("Complex Logic 2 " + task);
      System.out.println("Complex Logic 3 " + task);

      if (cr323) {
         System.out.println("Logic just for CR323 : " + task); // temporal coupling to the before/after
      }

      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
   }


   // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================

   // see the tests
   public void bossLevel(boolean fluff, List<Task> tasks) {
      int index = 0; // TODO ALT-ENTER move closer to usages
      int j = tasks.size();
      System.out.println("Logic1");
      List<Integer> taskIds = new ArrayList<>();
      System.out.println("Logic2");
      if (fluff) {
         System.out.println("Logic3");
         for (Task task : tasks) {
            System.out.println("Logic4: Validate " + task);
            task.setRunning();

            taskIds.add(task.getId());

            // TODO When **I** call this method, I want this to run HERE, too:
            // System.out.println("My Logic: " + task);

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