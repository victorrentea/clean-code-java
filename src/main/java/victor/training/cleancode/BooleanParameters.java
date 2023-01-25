package victor.training.cleancode;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Implement the variation required for CR323 without adding a boolean parameter
 */
public class BooleanParameters {
   @GetMapping("some/url")
   public void pretendEndpoint() {
      serviceMethod(2, new Task(4));
   }

   private static void serviceMethod(int id, Task task) {
      bigUglyMethod(id, task);
   }

   public static void main(String[] args) {
      // The big method is called from various foreign places in the codebase
      bigUglyMethod(1, new Task(5));
      bigUglyMethod(3, new Task(3));
      bigUglyMethod(4, new Task(2));
      bigUglyMethod(5, new Task(1));

      // TODO From my use-case #323, I call it too, to do more within:
      Task task = new Task(1);
      bigUglyMethod(2, task);

   }

      static void bigUglyMethod(int id, Task task) {
      System.out.println("Complex Logic 1 " + task + " and " + id);
      System.out.println("Complex Logic 2 " + task);
      System.out.println("Complex Logic 3 " + task);

      // System.out.println("Logic just for CR#323 : " + task);

      System.out.println("More Complex Logic " + id);
      System.out.println("More Complex Logic " + id);
      System.out.println("More Complex Logic " + id);
   }


   // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================

   // Lord gave us tests!
   public void bossLevel(boolean fluff, List<Task> tasks, boolean cr323) {
      int index = 0; // TODO move closer to usages in a safe way
      int j = tasks.size();
      System.out.println("Logic1");
      List<Integer> taskIds = new ArrayList<>();
      System.out.println("Logic2");
      if (fluff) {
         System.out.println("Logic3");
         for (Task task : tasks) {
            System.out.println("Logic4: Validate " + task);
            task.setStarted();

            taskIds.add(task.getId());

            if (cr323) { // TODO remove the boolean
               System.out.println("My Logic: " + task);
            }

            index++;
            System.out.println("Audit task index=" + index + ": " + task);
         }
         System.out.println("Logic6 " + j);
         System.out.println("Task Ids: " + taskIds);
      } else {
         System.out.println("Logic7 " + tasks);
      }
      System.out.println("Logic7");
   }

}


class Task {
   private final int id;
   private boolean started;

   Task(int id) {
      this.id = id;
   }

   public void setStarted() {
      started = true;
   }

   public boolean isStarted() {
      return started;
   }

   public int getId() {
      return id;
   }

   @Override
   public String toString() {
      return "Task{" + "id=" + id + ", started=" + started + '}';
   }
}