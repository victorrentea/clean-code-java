package victor.training.cleancode;

import java.util.ArrayList;
import java.util.List;

public class BooleanParameters {
   public static void main(String[] args) {
      // The big method is called from various foreign places in the codebase
      cancelOrder(5, 1);
      cancelOrder(4, 2);
      cancelOrder(3, 3);
      cancelOrder(2, 4);
      cancelOrder(1, 5);

      // TODO CR: From my use-case #323, I call it too, to do more within:
      cancelOrderNoua(1, 2);

   }




   static void cancelOrder(int orderId, int b) {
      beforeLogic(orderId, b);
      afterLogic(b);
   }

   static void cancelOrderNoua(int orderId, int b) {
      beforeLogic(orderId, b);
      System.out.println("Logica in plus! doar pt CR323" + orderId);
      afterLogic(b);
   }

   private static void afterLogic(int b) {
      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
   }

   private static void beforeLogic(int orderId, int b) {
      System.out.println("Complex Logic 1 " + orderId + " and " + b);
      System.out.println("Complex Logic 2 " + orderId);
      System.out.println("Complex Logic 3 " + orderId);
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