package victor.training.cleancode;

import java.util.List;
import java.util.stream.Collectors;

public class BooleanParameters {
   private Long state; // temporary field

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


   // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================

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

   public void bossLevelStuffFluffMy(List<Task> tasks) {
      bossStart(tasks);
      for (Task task : tasks) {
         // TODO When **I** call this method, I want this to run HERE, too:
         System.out.println("My Logic: " + task);
      }
      bossEnd(tasks);
   }
   public void bossLevelStuffFluff(List<Task> tasks) {
      bossStart(tasks);
      bossEnd(tasks);
   }

   private void bossEnd(List<Task> tasks) {
      for (int i = 0; i < tasks.size(); i++) {
         Task task = tasks.get(i);
         System.out.println("Logic5 " + (i + 1) + " on " + task.isRunning());
      }
      List<Long> taskIds = tasks.stream().map(Task::getId).collect(Collectors.toList());
      System.out.println("Logic6 " + tasks.size());
      System.out.println("Task Ids: " + taskIds);
      System.out.println("Logic7");
   }

   private void bossStart(List<Task> tasks) {
      System.out.println("Logic1");
      System.out.println("Logic2");
      System.out.println("Logic3");
      for (Task task : tasks) {
         System.out.println("Logic4: Validate " + task);
      }
      tasks.forEach(Task::setRunning);
   }

   private void innocentFunction(List<Task> tasks) { // do not mutate parameters
      tasks.add(new Task());
   }

   private void unkownJungleCode(Long id) {
      state = id;
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