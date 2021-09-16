package victor.training.cleancode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BooleanParameters {
   private static void cc() {
      bigUglyMethod(2, 4);
      bb();
   }

   private static void bb() {
      aa();
   }

   private static void aa() {
      bigUglyMethod(3, 3);
      bigUglyMethod(4, 2);
      bigUglyMethod(5, 1);
   }

   public static void main(String[] args) {
      // The big method is called from various foreign places in the codebase
      bigUglyMethod(1, 5);
      cc();

      // TODO From my use-case #323, I call it too, to do more within:
      bigUglyMethod323(2, 1);

   }

   static void bigUglyMethod323(int b, int a) {

      beforeLogic(b, a);

      System.out.println("LINIA TA pt cr323");

      afterLogic(b);
   }

   static void bigUglyMethod(int b, int a) {
      beforeLogic(b, a);
      afterLogic(b);
   }

   private static void beforeLogic(int b, int a) {
      System.out.println("Complex Logic 1 " + a + " and " + b);
      System.out.println("Complex Logic 2 " + a);
      System.out.println("Complex Logic 3 " + a);
      System.out.println("Complex Logic 1 " + a + " and " + b);
      System.out.println("Complex Logic 2 " + a);
      System.out.println("Complex Logic 3 " + a);
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

   {
      // jar de demodificat:
      bossLevel(true, false, Collections.emptyList());
      bossLevel(true, true, Collections.emptyList());
      bossLevel(true, false, Collections.emptyList());
      bossLevel(true, false, Collections.emptyList());
      bossLevel(true, false, Collections.emptyList());
      bossLevel(true, false, Collections.emptyList());

      // TU pe CR323
      bossLevel(true, false, Collections.emptyList(), true);

   }
   public void bossLevel(boolean stuff, boolean fluff, List<Task> tasks) { // overload // frozen api
      bossLevel(stuff, fluff, tasks, false);
   }
   public void bossLevel(boolean stuff, boolean fluff, List<Task> tasks, boolean cr323) {
      int index = 0; // TODO move closer to usages
      int j = tasks.size();
      System.out.println("Logic1");
      List<Long> taskIds = new ArrayList<>();
      if (stuff) {
         System.out.println("Logic2");
         if (fluff) {
            System.out.println("Logic3");
            for (Task task : tasks) {
               System.out.println("Logic4: Validate " + task);
               task.setRunning();

               taskIds.add(task.getId());

               // TODO When **I** call this method, I want this to run HERE, too:
               if (cr323) {
                  System.out.println("My Logic: " + task);
               }

               index++;
               System.out.println("Logic5 " + index + " on " + task.isRunning());
            }
            System.out.println("Logic6 " + j);
            System.out.println("Task Ids: " + taskIds);
         } else {
            System.out.println("Logic7 " + tasks);
         }
      }
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