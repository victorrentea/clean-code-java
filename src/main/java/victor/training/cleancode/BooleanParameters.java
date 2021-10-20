package victor.training.cleancode;

import victor.training.cleancode.support.Task;

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
      beforeLogic(b, task);
      afterLogic(b);
   }

   static void bigUglyMethod323(int b, Task task) {
      beforeLogic(b, task);
      System.out.println("Logic just for CR323 : " + task);
      afterLogic(b);
   }

   // This creates another problem, which is,
   // how to give meaningful names to your functions

   // BAD NEWS: that means you have to UNDERSTAND what  is goind on in there
   private static void beforeLogic(int b, Task task) {
      System.out.println("Complex Logic 1 " + task + " and " + b);
      System.out.println("Complex Logic 2 " + task);
      System.out.println("Complex Logic 3 " + task);
   }

   private static void afterLogic(int b) {
      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
   }


   // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================

   // see the tests
   public void bossLevelFluff(List<Task> tasks) {
      List<Integer> taskIds = bossBefore(tasks);
      bossAfter(tasks, taskIds);
   }
   public void bossLevelFluff323(List<Task> tasks) {
      List<Integer> taskIds = bossBefore(tasks);


      // I like to think of it this way: Whats more important to you?
      // Code thats 0.001 ms faster or easier to read+work-with?
      for (int i = 0; i < tasks.size(); i+=5) {
         tasks.get(i).doIt();
         tasks.get(i+1).doIt();
         tasks.get(i+2).doIt();
         tasks.get(i+3).doIt();
         tasks.get(i+5).doIt();
      }
      bossAfter(tasks, taskIds);
   }

   private void bossAfter(List<Task> tasks, List<Integer> taskIds) {
      int index = 0;
      for (Task task : tasks) {
         index++;
         System.out.println("Logic5 " + index + " on " + task.isRunning());
      }
      System.out.println("Logic6 " + tasks.size());
      System.out.println("Task Ids: " + taskIds);
      System.out.println("Logic7");
   }

   private List<Integer> bossBefore(List<Task> tasks) {
      System.out.println("Logic1");
      System.out.println("Logic2");
      System.out.println("Logic3");
      for (Task task : tasks) {
         System.out.println("Logic4: Validate " + task);
         task.setRunning();
      }
      List<Integer> taskIds = tasks.stream().map(Task::getId).collect(toList());
      return taskIds;
   }

   // see the tests
   public void bossLevelNoFluff(List<Task> tasks) {
      System.out.println("Logic1");
      System.out.println("Logic2");
      System.out.println("Logic7 " + tasks);
      System.out.println("Logic7");
   }

}


