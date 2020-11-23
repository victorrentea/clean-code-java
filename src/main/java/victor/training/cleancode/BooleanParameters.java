package victor.training.cleancode;

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
      int j = before2(b, a);
      int k = before1(a);

      afterLogic(b, j, k);
   }
   static void bigUglyMethod323(int b, int a) {
      int j = before2(b, a);
      int k = before1(a);

      System.out.println("Doar al meu " + b);

      afterLogic(b, j, k);
   }

   private static void afterLogic(int b, int j, int k) {
      System.out.println("More Complex Logic " + b + j);
      System.out.println("More Complex Logic " + b + k);
      System.out.println("More Complex Logic " + b);
   }

   private static int before2(int b, int a) {
      int j =1;
      System.out.println("Complex Logic 1 " + a + " and " + b);
      System.out.println("Complex Logic 2 " + a);
      return j;
   }

   private static int before1(int a) {
      int k =2;
      System.out.println("Complex Logic 3 " + a);
      return k;
   }


   // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================

   public void bossLevelStuffSFluff(List<Task> tasks) {
      System.out.println("Logic1");
      System.out.println("Logic2");
      System.out.println("Logic3");
      int index = 0;
//      var importer = new Importer();
      for (Task task : tasks) {
         System.out.println("Logic4: Validate " + task);
         task.start();
//         importer.setTask(task); // temporary field code smell
         // index++ ; ambele modifica chestii in afara forului de care al doilea for are nevoie.
      }
      for (Task task : tasks) {
         // TODO When **I** call this method, I want this to run HERE, too:
         System.out.println("My Logic: " + task);
      }
      for (Task task : tasks) {
         index++;
//         importer.run();
         System.out.println("Logic5 " + index + " on " + task.isRunning());

      }
      System.out.println("Logic6 " + tasks.size());
      System.out.println("Logic7");
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
   private boolean running;

   public void start() {
      running = true;
   }

   public boolean isRunning() {
      return running;
   }
}