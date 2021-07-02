package victor.training.cleancode;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class BooleanParameters {
   public static void main(String[] args) {
      // The big method is called from various foreign places in the codebase
      bigUglyMethod(1, 5);
      bigUglyMethod(2, 4);
      bigUglyMethod(3, 3);
      bigUglyMethod(4, 2);
      bigUglyMethod(5, 1);

      // TODO From my use-case #323, I call it too, to do more within:
      bigUglyMethod(2, 1);


      // TODO From my use-case #325, I call it too, to do more within:
      bigUglyMethod323(2, 1);

   }

   static void bigUglyMethod(int b, int a) {
      beforeLogic(b, a);
      after(b);
   }
   static void bigUglyMethod323(int b, int a) {
      beforeLogic(b, a);
      System.out.println("#sieu pt cr323"); // doar cand o chem eu
      after(b);
   }

   private static void after(int b) {
      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
   }

   private static void beforeLogic(int b, int a) {
      System.out.println("Complex Logic 1 " + a + " and " + b);
      System.out.println("Complex Logic 2 " + a);
      System.out.println("Complex Logic 3 " + a);
   }


   // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================
   public void clientuSaracu() {
      bossLevelStuffFluff(new ArrayList<>());
   }

   public void bossLevelStuffFluffAMea(List<Task> tasks) {
      beforeBoss(tasks);

      for (Task task : tasks) {
         System.out.println("My Logic: " + task);
         // TODO When **I** call this method, I want this to run HERE, too:
      }
      afterBoss(tasks);
   }
   public void bossLevelStuffFluff(List<Task> tasks) {
      beforeBoss(tasks);
      afterBoss(tasks);
   }

   private void afterBoss(List<Task> tasks) {
      for (int i = 0; i < tasks.size(); i++) {
         Task task = tasks.get(i);
         System.out.println("Logic5 " + (i+1) + " on " + task.isRunning());
      }
      System.out.println("Logic6 " + tasks.size());
      List<Long> taskIds = tasks.stream().map(Task::getId).collect(toList());
      System.out.println("Task Ids: " + taskIds);
      System.out.println("Logic7");
   }

   private void beforeBoss(List<Task> tasks) {
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
   public void bossLevelNoStuff(List<Task> tasks) {
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