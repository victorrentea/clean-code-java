package victor.training.cleancode;

import java.util.ArrayList;
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
      bigUglyMethod323(2, 1, true);

   }

   static void bigUglyMethod323(int b, int a, boolean cr323) {

   }
   static void bigUglyMethod(int b, int a) {
      System.out.println("Complex Logic 1 " + a + " and " + b);
      System.out.println("Complex Logic 2 " + a);
      System.out.println("Complex Logic 3 " + a);

      if (false) {
         System.out.println("HERE I want something here !!!");
      }

      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
   }







   // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================

   public void bossLevelStuffFluff(List<Task> tasks) {
      int j = tasks.size();
      System.out.println("Logic1");
      List<Long> taskIds = new ArrayList<>();
      System.out.println("Logic2");
      System.out.println("Logic3");
      int index = 0;
      for (Task task : tasks) {
         unkownJungleCode(task.getId());
         System.out.println("Logic4: Validate " + task);
         task.setRunning();
      }
      for (Task task : tasks) {
         taskIds.add(task.getId());
      }
      for (Task task : tasks) {
         // TODO When **I** call this method, I want this to run HERE, too:
         System.out.println("My Logic: " + task);
      }
      for (Task task : tasks) {
         otherUnkownJUngleCode(); // hidden temporal coupling
         index++;
         System.out.println("Logic5 " + index + " on " + task.isRunning());
      }
      System.out.println("Logic6 " + j);
      System.out.println("Task Ids: " + taskIds);
      System.out.println("Logic7");
   }


   private Long state; // temporary field
   private void unkownJungleCode(Long id) {
      state=id;
   }
   private void otherUnkownJUngleCode() {
      System.out.println("With task id " + state);
   }

   public void bossLevelStuffNoFluff(List<Task> tasks) {
      System.out.println("Logic1");
      System.out.println("Logic2");
      System.out.println("Logic7 " + tasks);
      System.out.println("Logic7");
   }
   public void bossLevelNoStuff() {
      System.out.println("Logic1");
      System.out.println("Logic7");
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