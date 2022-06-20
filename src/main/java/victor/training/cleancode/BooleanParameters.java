package victor.training.cleancode;

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

      // TODO From my use-case #323, I call it too, to do more within:
      Task task = new Task(1);
      bigUglyMethod323(2, task);
   }

   static void bigUglyMethod(int b, Task task) {
      bigStart(b, task);
      bigEnd(b);
   }
   static void bigUglyMethod323(int b, Task task) {
      bigStart(b, task);
      System.out.println("Logic just for CR323 : " + task);
      bigEnd(b);
   }

   private static void bigEnd(int b) {
      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
   }

   private static void bigStart(int b, Task task) {
      System.out.println("Complex Logic 1 " + task + " and " + b);
      System.out.println("Complex Logic 2 " + task);
      System.out.println("Complex Logic 3 " + task);
   }


   // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================

   // Lord gave us tests!
   public void bossLevel(boolean fluff, List<Task> tasks, boolean cr323) {
//      int j = tasks.size();
      System.out.println("Logic1");
      List<Integer> taskIds = new ArrayList<>();
      System.out.println("Logic2");
      if (fluff) {
         innocent(tasks); // java sucks, 26 years old.
         System.out.println("Logic3");
         int index = 0;
         for (Task task : tasks) {
            System.out.println("Logic4: Validate " + task);
            task.setRunning();

            taskIds.add(task.getId());

            if (cr323) { // TODO remove the boolean
               System.out.println("My Logic: " + task);
            }

            index++;
            System.out.println("Logic5 index=" + index + " on running=" + task.isRunning());
         }
         int j = tasks.size();
         System.out.println("Logic6 " + j);
         System.out.println("Task Ids: " + taskIds);
      } else {
         System.out.println("Logic7 " + tasks);
      }
      System.out.println("Logic7");
   }

   private void innocent(List<Task> tasks) {
      tasks.clear(); // you are reassigned to a lovely cobol project
   }

}


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

   @Override
   public String toString() {
      return "Task{" + "id=" + id + ", running=" + running + '}';
   }
}