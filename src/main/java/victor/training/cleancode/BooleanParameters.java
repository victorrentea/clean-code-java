package victor.training.cleancode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
      bigStart(b, a);
      bigEnd(b);
   }

   static void bigUglyMethod323(int b, int a) {
      bigStart(b, a);
      System.out.println("CR323 specific");
      bigEnd(b);
   }

   private static void bigStart(int b, int a) {
      System.out.println("Complex Logic 1 " + a + " and " + b);
      System.out.println("Complex Logic 2 " + a);
      System.out.println("Complex Logic 3 " + a);
   }

   private static void bigEnd(int b) {
      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
   }


   // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================

   {
      bossLevelStuffFluff(Collections.emptyList());
      bossLevelStuffNoFluff(Collections.emptyList());
      bossLevelNoStuff(Collections.emptyList());
      bossLevelNoStuff(Collections.emptyList());

      //cr323
      bossLevelStuffFluff323(Collections.emptyList());

   }

   public void bossLevelStuffFluff(List<Task> tasks) {
      pre(tasks);
      after(tasks);
   }
   public void bossLevelStuffFluff323(List<Task> tasks) {
      pre(tasks);
      for (Task task : tasks) {
         System.out.println("My Logic: " + task);
      }
      after(tasks);
   }

   private void after(List<Task> tasks) {
      List<Long> taskIds = tasks.stream().map(Task::getId).collect(Collectors.toList());

      for (int i = 0; i < tasks.size(); i++) {
         Task task = tasks.get(i);
         System.out.println("Logic5 " + (i + 1) + " on " + task.isRunning());
      }
      System.out.println("Logic6 " + tasks.size());
      System.out.println("Task Ids: " + taskIds);
      System.out.println("Logic7 " + tasks.size());
   }

   private void pre(List<Task> tasks) {
      System.out.println("Logic1");
      System.out.println("Logic2");
      System.out.println("Logic3");
      for (Task task : tasks) {
         System.out.println("Logic4: Validate " + task);
         task.setRunning();
      }
   }


   public void bossLevelStuffNoFluff(List<Task> tasks) {
      System.out.println("Logic1");
      System.out.println("Logic2");
      System.out.println("Logic7 " + tasks);
      System.out.println("Logic7 " + tasks.size());
   }
   public void bossLevelNoStuff(List<Task> tasks) {
      System.out.println("Logic1");
      System.out.println("Logic7 " + tasks.size());
   }


   private void innocent(List<Task> tasks) {

      tasks.clear();
   }

}


class Task {
   private Long id;
   private boolean running;

   public void setRunning() {
      running = true;
   }

   public boolean isRunning() {
      return running;
   }

   public Long getId() {
      return id;
   }
}