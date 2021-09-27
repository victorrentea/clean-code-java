package victor.training.cleancode;

import java.util.ArrayList;
import java.util.List;

public class BooleanParameters {
   public static void main(String[] args) {
      // The big method is called from various foreign places in the codebase
      cancelOrder(5, 1, false);
      cancelOrder(4, 2, false);
      cancelOrder(3, 3, false);
      cancelOrder(2, 4, false);
      cancelOrder(1, 5, false);

      // TODO CR: From my use-case #323, I call it too, to do more within:
      cancelOrder(1, 2, true);

   }




   static void cancelOrder(int orderId, int b, boolean cr323) {
      System.out.println("Complex Logic 1 " + orderId + " and " + b);
      System.out.println("Complex Logic 2 " + orderId);
      System.out.println("Complex Logic 3 " + orderId);

      if (cr323) {
         System.out.println("Logica in plus! doar pt CR323" + orderId);
      }

      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
   }










   // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================

   public void bossLevel(boolean stuff, boolean fluff, List<Task> tasks) {
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
               // System.out.println("My Logic: " + task);

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