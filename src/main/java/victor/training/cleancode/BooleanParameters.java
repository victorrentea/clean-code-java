package victor.training.cleancode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class BooleanParameters {
   {
      List<Task> list = new ArrayList<>();
      bossLevelStuffFluff(list);
      bossLevelStuffNoFluff(list);
      bossLevelNoStuff();
   }

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


   // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================

   private static void beforeLogic(int orderId, int b) {
      System.out.println("Complex Logic 1 " + orderId + " and " + b);
      System.out.println("Complex Logic 2 " + orderId);
      System.out.println("Complex Logic 3 " + orderId);
   }

   public void bossLevelStuffFluff(List<Task> tasks) {
      bossStart(tasks);

      numeDestept(tasks);

      bossEnd(tasks);
   }
   public void bossLevelStuffFluff323(List<Task> tasks) {
      bossStart(tasks);

      for (Task task : tasks) {
      // TODO When **I** call this method, I want this to run HERE, too:
         System.out.println("My Logic: " + task);
      }

      numeDestept(tasks);

      bossEnd(tasks);
   }

   private void numeDestept(List<Task> tasks) {
      int index = 0;
      for (Task task : tasks) {
         index++;
         System.out.println("Logic5 " + index + " on " + task.isRunning());
      }
   }

   private void bossStart(List<Task> tasks) {
      bossStart();
      for (Task task : tasks) {
         System.out.println("Logic4: Validate " + task);
         task.setRunning();
      }
   }

   private void bossEnd(List<Task> tasks) {
      List<Long> taskIds = tasks.stream().map(Task::getId).collect(toList());
//      List<Long> taskIds = new ArrayList<>();
//      for (Task task : tasks) {
//         taskIds.add(task.getId());
//      }
      System.out.println("Logic6 " + tasks.size());
      System.out.println("Task Ids: " + taskIds);
      System.out.println("Logic7");
   }

   private void bossStart() {
      System.out.println("Logic1");
      System.out.println("Logic2");
      System.out.println("Logic3");
   }

   public void bossLevelStuffNoFluff(List<Task> tasks) {
      System.out.println("Logic1");
      System.out.println("Logic2");
      System.out.println("Logic7 " + tasks);
      System.out.println("Logic7");
   }

   private void innocent(List<Task> tasks) {
      tasks.clear(); // NICIODATA SA NU MODIFICI COLECTIILE DATE PARAM FUNCTIEI TALE
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