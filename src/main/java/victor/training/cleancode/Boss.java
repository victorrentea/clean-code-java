package victor.training.cleancode;

import com.google.common.collect.ImmutableList;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

// Deep nested functions are harder to break down
public class Boss {

  // 👌 TODO run tests
  public void bossLevel(boolean forNL, ImmutableList<Task> tasks, boolean cr323) {
    if (tasks.isEmpty()) return;
    System.out.println("Logic1");
    List<Integer> taskIds = new ArrayList<>();
    if (forNL) {
      System.out.println("Logic3");
      check(tasks); // when a method does not return you anything, that method must change stuff (or throw)
      int index = 0;
      for (Task task : tasks) {
        System.out.println("Starting " + task);
        task.setStarted(true);

        taskIds.add(task.getId());

        if (cr323) { // TODO remove the boolean
          System.out.println("My Logic: " + task);
        }
        index++;
        System.out.println("Audit task #" + index + ": " + task);
      }

      System.out.println("Logic6 " + tasks.size());
      System.out.println("Task Ids: " + taskIds);
    } else {
      System.out.println("Logic7 on fluff=false " + tasks);
    }
    System.out.println("Logic8");
  }

  private void check(ImmutableList<Task> tasks) {
    // Immutable List from Guava implements List but deprecated all the mutating methods
    // do not mix ImmutableList with Hibernate @Entity
    //tasks.remove(0); // surprise
  }
}
