package victor.training.cleancode;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Implement the variation required for CR323 without adding a boolean parameter
 */
public abstract class BooleanParameters {

   public static void main(String[] args) {
//      new BooleanParameters().f();

   }

   private void f() {
      // The big method is called from various foreign places in the codebase
      Proiecte proiecte = new Proiecte();
      bigUglyMethod(1, new Task(5), task1 -> proiecte.verde(task1));
      bigUglyMethod(2, new Task(4), task1 -> proiecte.verde(task1));
      bigUglyMethod(3, new Task(3), task1 -> proiecte.verde(task1));
      bigUglyMethod(4, new Task(2), task1 -> proiecte.verde(task1));
      bigUglyMethod(5, new Task(1), task1 -> proiecte.verde(task1));

      // TODO From my use-case #323, I call it too, to do more within:
      Task task = new Task(1);
//      bigUglyMethod323(2, task);
   }

   public void bigUglyMethod(int fas, Task task, Consumer<Task> pasuLipsa) {
      beforeStuff(fas, task);

      pasuLipsa.accept(task);

      afterStuff(fas);
   }

   private void afterStuff(int fas) {
      System.out.println("More Complex Logic " + fas);
      System.out.println("More Complex Logic " + fas);
      System.out.println("More Complex Logic " + fas);
   }

   public void beforeStuff(int fas, Task task) {
      System.out.println("Complex Logic 1 " + task + " and " + fas);
      System.out.println("Complex Logic 2 " + task);
      System.out.println("Complex Logic 3 " + task);
   }

}
class Proiecte  {
   public void verde(Task task) {
      System.out.println("verde§ : " + task);
      System.out.println("verde : " + task);
      System.out.println("verde : " + task);
   }
   public void maro(Task task) {
      System.out.println("cacaniu : " + task);
      System.out.println("cacaniu : " + task);
      System.out.println("cacaniu : " + task);
      System.out.println("cacaniu : " + task);
   }

}
class X {



   // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================

   // Lord gave us tests!
   public void bossLevel(boolean fluff, List<Task> tasks, boolean cr323) {
      int index = 0; // TODO move closer to usages
      int jj = tasks.size();
      System.out.println("Logic1");
      List<Integer> taskIds = new ArrayList<>();
      System.out.println("Logic2");
      if (fluff) {
         System.out.println("Logic3");
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
         System.out.println("Logic6 " + jj);
         System.out.println("Task Ids: " + taskIds);
      } else {
         System.out.println("Logic7 " + tasks);
      }
      System.out.println("Logic7");
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