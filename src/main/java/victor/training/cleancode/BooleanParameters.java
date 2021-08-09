package victor.training.cleancode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
      bossLevelStuffFluff(Collections.emptyList(), false);
      bossLevelStuffNoFluff(Collections.emptyList());
      bossLevelNoStuff(Collections.emptyList());
      bossLevelNoStuff(Collections.emptyList());

      //cr323
      bossLevelStuffFluff(Collections.emptyList(), true);

   }

   public void bossLevelStuffFluff(List<Task> tasks, boolean cr323) {
      System.out.println("Logic1");
      List<Long> taskIds = new ArrayList<>();
      System.out.println("Logic2");
      System.out.println("Logic3");
      int index = 0;
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
      System.out.println("Logic6 " + tasks.size());
      System.out.println("Task Ids: " + taskIds);
      System.out.println("Logic7 " + tasks.size());
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