package victor.training.cleancode;

import org.jooq.lambda.fi.util.function.CheckedConsumer;

import java.io.FileWriter;
import java.io.Writer;
import java.util.List;

import static java.util.stream.Collectors.toList;

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

   /// alt exemplu
   public void exportu1() throws Throwable {
      exporta( writer -> {
         System.out.println("CEVA FOARTE VARIABIL (ai 7 implementari aici)");
         writer.write("Ceva1");
         writer.write("Ceva1");
         writer.write("Ceva");
      });
   }
   public void exportu2() throws Throwable {
      exporta( writer -> {
         System.out.println("CEVA FOARTE VARIABIL (ai 7 implementari aici)");
         writer.write("Ceva2");
         writer.write("Ceva2");
         writer.write("Ceva");
      });
   }

   public void exporta(CheckedConsumer<Writer> writeContentFunction) throws Throwable {
      try {
         System.out.println("Logica de inceput");
         System.out.println("Logica");

         try (FileWriter writer = new FileWriter("out.txt")) {
            writeContentFunction.accept(writer);
         }
         System.out.println("Logica de final");
      } catch (Exception ex) {
         // nimic aici. happy debugging, suckers!
      }

   }


   // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================

   {

   }
   public void bossLevelStuffFluff(List<Task> tasks) {
      beforeBoss(tasks);
      afterBoss(tasks);
   }
   public void bossLevelStuffFluffcr323(List<Task> tasks) {
      beforeBoss(tasks);

      for (Task task : tasks) {
         System.out.println("My Logic: " + task);
      }
      afterBoss(tasks);
   }

   private void afterBoss(List<Task> tasks) {
      int index = 0;
      for (Task task : tasks) {
         index++;
         System.out.println("Logic5 " + index + " on " + task.isRunning());
      }
      System.out.println("Logic6 " + tasks.size());
      List<Long> taskIds = collectTaskIds(tasks);
      System.out.println("Task Ids: " + taskIds);
      System.out.println("Logic7");
   }

   private List<Long> collectTaskIds(List<Task> tasks) {
      return tasks.stream().map(Task::getId).collect(toList());
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