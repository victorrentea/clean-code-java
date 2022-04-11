package victor.training.cleancode.idee2_statefulClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Implement the variation required for CR323 without adding a boolean parameter
 */
public class BooleanParameters_MethodObject {
   // PROBLEM1 you need a new instance of this class everytime you call BossLevel.
   // that's not BAD for performance, but BAD for testing
   // consider a usecase using it
   public void usecase() {
      // LOGICA ASTA DE AICI , cum mama ma-sii mockuiesti urm linie ? @Mock
      new BooleanParameters_MethodObject(
               true, new ArrayList<>(), false)
          .bossLevel();
   }
   private final boolean fluff;
   private final List<Task> tasks;
   private final boolean cr323;

   // PROBLEM2: "Temporary field" Code smells > se modifica de-a ungul vietii accestei instante.
   // variabilele traiesc prea MULT TIMP.
   private int j;
   int index = 0;
   List<Integer> taskIds = new ArrayList<>();

   public BooleanParameters_MethodObject(boolean fluff, List<Task> tasks, boolean cr323) {
      this.fluff = fluff;
      this.tasks = tasks;
      this.cr323 = cr323;
      j = tasks.size();
   }

   public void bossLevel() {
      System.out.println("Logic1");
      System.out.println("Logic2");
      if (fluff) {
         fluffBranch();
      } else {
         System.out.println("Logic7 " + tasks);
      }
      System.out.println("Logic7");
   }

   private void fluffBranch() {
      System.out.println("Logic3");
      for (Task task : tasks) {
         foru(task);
      }
      System.out.println("Logic6 " + j);
      System.out.println("Task Ids: " + taskIds);
   }

   private void foru(Task task) {
      System.out.println("Logic4: Validate " + task);
      task.setRunning();

      taskIds.add(task.getId());

      if (cr323) { // TODO remove the boolean
         System.out.println("My Logic: " + task);
      }
      index++;
      System.out.println("Logic5 index=" + index + " on running=" + task.isRunning());
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