package victor.training.cleancode;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implement the variation required for CR323 without adding a boolean parameter
 */

public class BooleanParameters {

   public static void main(String[] args) {
      // The big method is called from various foreign places in the codebase that I don't know of
      bigUglyMethod(1, new Task(5));
      bigUglyMethod(2, new Task(4));
      bigUglyMethod(3, new Task(3));
      bigUglyMethod(4, new Task(2));
      bigUglyMethod(5, new Task(1));

      // TODO From my use-case #323, I call it too, to do more within:. + task.setDetail(15)
      Task task = new Task(1);
      bigUglyMethod323(2, task);

      // CR325
      bigUglyMethod(2, new Task(1));

   }

   static void bigUglyMethod(int b, Task task) {
      before(b, task);
      after(b);
   }

   static void bigUglyMethod323(int b, Task task) {
      before(b, task);
      System.out.println("Logic just for CR323 : " + task); // temporal coupling to the before/after
      after(b);
   }

   private static void before(int b, Task task) {
      System.out.println("Complex Logic 1 " + task + " and " + b);
      System.out.println("Complex Logic 2 " + task);
      System.out.println("Complex Logic 3 " + task);
   }

   private static void after(int b) {
      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
   }


   // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================

   // see the tests
   public void bossLevel(List<Task> tasks) {
      List<Integer> taskIds = bossStart(tasks);
      bossEnd(tasks, taskIds);
   }
   public void bossLevel323(List<Task> tasks, boolean cr323) {
      List<Integer> taskIds = bossStart(tasks);

      for (Task task : tasks) {
         System.out.println("My Logic: " + task);
      }

      bossEnd(tasks, taskIds);
   }

   private void bossEnd(List<Task> tasks, List<Integer> taskIds) {
      int index = 0;
      for (Task task : tasks) {
         index++;
         System.out.println("Logic5 " + index + " on " + task.isRunning());
      }
      System.out.println("Logic6 " + tasks.size());
      System.out.println("Task Ids: " + taskIds);
      System.out.println("Logic7");
   }

   private List<Integer> bossStart(List<Task> tasks) {
      System.out.println("Logic1");
      System.out.println("Logic2");
      System.out.println("Logic3");
      for (Task task : tasks) {
//         index++; // BUG: change of state outside of the loop, used later in a 2nd loop.
         // similar but more evil: stateful interaction with a 3rd party service :
         // eg: connect() send() receive() disconnect() .. if they use SESSION - very rare.
         // DOESNT HAPPEN FOR REST/SOAP/RMI (USUALLY)
         System.out.println("Logic4: Validate " + task);
         task.setRunning();
      }
      List<Integer> taskIds = tasks.stream().map(Task::getId).collect(Collectors.toList());
 //      taskIds = tasks.stream().map(Task::getId).collect(Collectors.toList());

      return taskIds;
   }

   private void innocentMethod(List<Task> tasks) {
      tasks.clear(); // NEVER EVER EVER EVER mutate collections given as params (removing)
      // Java is old. 26 years old. '95   >> java standard collections are mutable - MISTAKE
//      tasks.add(new )
   }

   public void bossLevelNoFluff(List<Task> tasks) {
      System.out.println("Logic1");
      System.out.println("Logic2");
      System.out.println("Logic7 " + tasks);
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