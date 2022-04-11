package victor.training.cleancode.idee1_paramObj;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * Implement the variation required for CR323 without adding a boolean parameter
 */
public class BooleanParameters {

   public void bossLevel(BossParamsObject params) {
      int index = 0; // TODO move closer to usages
      int j = params.getTasks().size();
      System.out.println("Logic1");
      List<Integer> taskIds = new ArrayList<>();
      System.out.println("Logic2");
      if (params.isFluff()) {
         fluffBranch(params, index, j, taskIds);
      } else {
         System.out.println("Logic7 " + params.getTasks());
      }
      System.out.println("Logic7");
   }

   private void fluffBranch(BossParamsObject params, int index, int j, List<Integer> taskIds) {
      System.out.println("Logic3");
      for (Task task : params.getTasks()) {
         System.out.println("Logic4: Validate " + task);
         task.setRunning();

         taskIds.add(task.getId());

         if (params.isCr323()) { // TODO remove the boolean
            System.out.println("My Logic: " + task);
         }

         index++;
         System.out.println("Logic5 index=" + index + " on running=" + task.isRunning());
      }
      System.out.println("Logic6 " + j);
      System.out.println("Task Ids: " + taskIds);
   }

}

// PROBLEM:  this "Parameter Object" is only used 100% for one method invocation.
// when calling fluffBranch(), only 2 fields out of 3 are really needed.
// If it was used 100% for many method calls, it might be a good idea!
@Value
class BossParamsObject {
   boolean fluff;
   List<Task> tasks;
   boolean cr323;
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