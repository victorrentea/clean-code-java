package victor.training.cleancode;

import java.util.ArrayList;
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
      System.out.println("#sieu inainte");
      bigUglyMethod323(2, 1);
      System.out.println("#sieu dupa");

   }

   static void bigUglyMethod(int b, int a) {
      beforeLogic(b, a);
      afterLogic(b);
   }

   static void bigUglyMethod323(int b, int a) {
      beforeLogic(b, a);
      System.out.println("In matzele codului existent, hop #sieu");
      afterLogic(b);
   }

   private static void beforeLogic(int b, int a) {
      System.out.println("Complex Logic 1 " + a + " and " + b);
      System.out.println("Complex Logic 2 " + a);
      System.out.println("Complex Logic 3 " + a);
   }

   private static void afterLogic(int b) {
      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
   }


   // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================
   public void method() {

//      bossLevelStuff(...);//bossLevel(true,...);
//      bossLevelNoStuff(...);//bossLevel(false, ...);
   }

   public void bossLevelStuffFluff(List<Task> tasks, boolean cr323) {
      bossBefore(tasks);
      bossAfter(tasks);
   }
   public void bossLevelStuffFluff323(List<Task> tasks, boolean cr323) {
      bossBefore(tasks);
      for (Task task : tasks) {
         System.out.println("My Logic: " + task);
      }
      bossAfter(tasks);
   }

   private void bossAfter(List<Task> tasks) {
      int index = 0;
      for (Task task : tasks) {
         index++;
         System.out.println("Logic5 " + index + " on " + task.isRunning());
      }
      System.out.println("Logic6 " + tasks.size());
      List<Long> taskIds = tasks.stream().map(Task::getId).collect(Collectors.toList());
      System.out.println("Task Ids: " + taskIds);
      System.out.println("Logic7");
   }

   private void bossBefore(List<Task> tasks) {
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