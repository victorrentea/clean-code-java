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
      bigUglyMethod323(task);

   }

   private static void bigUglyMethod(int b, Task task) {
      bigUglyStart(b, task);
      bigUglyEnd(b);
   }

   private static void bigUglyMethod323(Task task) {
      bigUglyStart(2, task);
      System.out.println("Logic just for CR323 : " + task);
      bigUglyEnd(2);
   }

   private static void bigUglyStart(int b, Task task) {
      System.out.println("Complex Logic 1 " + task + " and " + b);
      System.out.println("Complex Logic 2 " + task);
      System.out.println("Complex Logic 3 " + task);
      System.out.println("Complex Logic 1 " + task + " and " + b);
      System.out.println("Complex Logic 2 " + task);
      System.out.println("Complex Logic 3 " + task);
   }

   private static void bigUglyEnd(int b) {
      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
   }


   // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================

   // see the tests
   private void bossLevelFluff(List<Task> tasks, boolean cr323) {
      List<Integer> taskIds = bossStart(tasks);

      for (Task task : tasks) {
         // TODO When **I** call this method, I want this to run HERE, too:
         if (cr323) {
            System.out.println("My Logic: " + task);
         }
      }
      bossEnd(tasks, taskIds);
   }

   private void bossEnd(List<Task> tasks, List<Integer> taskIds) {
      int index = 0;
      for (Task task : tasks) {
         index++;
         System.out.println("Logic5 " + index + " on " + task.isRunning());
      }
      // q1: ce poate merge rau:
      // - peformanta: cand avem multe (10K) taskuri
      //     1) cat de mare e un task in heap? INCAP toate? E wise sa tin 100MB in RAM cu toate? 1000.. mai mult, te gandesti la procesare in pagini eg de 100
      //     2) cat de multa munca faci per task e f f rapida > e posibil sa doara ca faci 4 foruri. In java asta - NICIODATA.
      /// ai emotii, fa benchmarkuri (in Java cu JMH)
      // q2: cand "Split Loop" refactor produce buguri
      // - cand depind unele de altele>
      System.out.println("Logic6 " + tasks.size());
      System.out.println("Task Ids: " + taskIds);
      System.out.println("Logic7");
   }

   private List<Integer> bossStart(List<Task> tasks) {
      System.out.println("Logic1");
      System.out.println("Logic2");
      System.out.println("Logic3");

      for (Task task : tasks) {
         System.out.println("Logic4: Validate " + task);
         task.setRunning();
      }

      List<Integer> taskIds = new ArrayList<>();
      for (Task task : tasks) {
         taskIds.add(task.getId());
      }
      return taskIds;
   }

   private void bossLevelNoFluff(List<Task> tasks) {
      System.out.println("Logic1");
      System.out.println("Logic2");
      System.out.println("Logic7 " + tasks);
      System.out.println("Logic7");
   }
   public void bossLevel(boolean fluff, List<Task> tasks, boolean cr323) {
      if (fluff) {
         bossLevelFluff(tasks, cr323);
      } else {
         bossLevelNoFluff(tasks);
      }

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