package victor.training.cleancode;

import java.util.ArrayList;
import java.util.List;

// Deep nested functions are harder to break down
public class Boss {

  // 👌 TODO run tests
  public void bossLevel(boolean b, List<Task> tasks, boolean cr323) {
    int index = 0;
    int taskCount = tasks.size();
    if (tasks.size() == 0) {
      return;
    }
    System.out.println("Logic1");
    List<Integer> taskIds = new ArrayList<>();
    if (b) {
      // for GDPR
      System.out.println("Logic3");
      check(tasks);
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
      System.out.println("Logic6 " + taskCount);
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
