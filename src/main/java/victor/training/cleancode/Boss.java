package victor.training.cleancode;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

// Deep nested functions are harder to break down
public class Boss {

  // 👌 TODO run tests
  public void bossLevel(boolean gdpr, ImmutableList<Task> tasks, boolean cr323) {
    if (tasks.isEmpty()) {
      return;
    }
    System.out.println("Logic1");
    if (gdpr) {
      System.out.println("Logic3");
      check(tasks);
      List<Integer> taskIds = new ArrayList<>();
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
      int taskSize = tasks.size();
      System.out.println("Logic6 " + taskSize);
      System.out.println("Task Ids: " + taskIds);
    } else {
      System.out.println("Logic7 on fluff=false " + tasks);
    }
    System.out.println("Logic8");
  }

  private void check(List<Task> tasks) {
//    tasks.remove(0); // surprise
  }
}
