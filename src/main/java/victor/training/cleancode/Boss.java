package victor.training.cleancode;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

// Deep nested functions are harder to break down
public class Boss {

  // 👌 TODO run tests
  public void bossLevel(ImmutableList<Task> tasks, boolean cr323) {
    if (tasks.isEmpty()) return;
    System.out.println("Logic1");
    System.out.println("Logic3");
    check(tasks);
    // we learned that fors COST TIME : O(N), O(N^2), if nested on on a  large collection
    // > performance hit? only if your response time < 1 ms (eg 50 microsec), 1 DB hit = 5-10ms
    // > bugs because actions happen in the wrong order
    List<Integer> taskIds = new ArrayList<>();
    int index = 0;
    for (Task task : tasks) {
      System.out.println("Starting " + task);
      task.setStarted(true);
    }
    for (Task task : tasks) {
      taskIds.add(task.getId());
    }
    for (Task task : tasks) {
      if (cr323) { // TODO remove the boolean
        System.out.println("My Logic: " + task);
      }
    }
    for (Task task : tasks) {
      // audit task
      index++;
      System.out.println("Audit task #" + index + ": " + task);
    }
    System.out.println("Logic6 " + tasks.size());
    System.out.println("Task Ids: " + taskIds);
    System.out.println("Logic8");
  }
  public void bossLevelNonEU(ImmutableList<Task> tasks) {
    if (tasks.isEmpty()) return;
    System.out.println("Logic1");
    System.out.println("Logic7 on fluff=false " + tasks);
    System.out.println("Logic8");
  }

  private void check(List<Task> tasks) {
//    tasks.remove(0); // surprise
  }
}
