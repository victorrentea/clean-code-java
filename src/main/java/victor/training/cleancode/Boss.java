package victor.training.cleancode;

import java.util.List;

// Deep nested functions are harder to break down
public class Boss {

  // 👌 TODO run tests
  public void bossLevel(boolean bulgaria, List<Task> tasks, boolean cr323) {
    int taskCount = tasks.size();
    if (tasks.isEmpty()) return;
    System.out.println("Logic1");
    if (bulgaria) {
      System.out.println("Logic3");
      check(tasks);
      startTasks(tasks);
      if (cr323) { // TODO remove the boolean
        for (Task task : tasks) {
          System.out.println("My Logic: " + task);
        }
      }
      auditTasks(tasks);
      List<Integer> taskIds = tasks.stream().map(Task::getId).toList();
      System.out.println("Logic6 " + taskCount);
      System.out.println("Task Ids: " + taskIds);
    } else {
      System.out.println("Logic7 on fluff=false " + tasks);
    }
    System.out.println("Logic8");
  }

  private void auditTasks(List<Task> tasks) {
    int index = 0;
    for (Task task : tasks) {
      // audit
      index++;
      System.out.println("Audit task #" + index + ": " + task);
    }
  }

  private void startTasks(List<Task> tasks) {
    for (Task task : tasks) {
      System.out.println("Starting " + task);
      task.setStarted(true);
    }
  }

  private void check(List<Task> tasks) {
//    tasks.remove(0); // surprise
  }
}
