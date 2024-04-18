package victor.training.cleancode;

import com.google.common.collect.ImmutableList;

import java.util.List;

// Deep nested functions are harder to break down
public class Boss {

  // 👌 TODO run tests
  public void bossLevel(boolean gdpr, ImmutableList<Task> tasks, boolean cr323) {
    if (tasks.isEmpty()) {
      return;
    }
    System.out.println("Logic1");
    if (!gdpr) {
      System.out.println("Logic7 on fluff=false " + tasks);
      System.out.println("Logic8");
      return;
    }
    System.out.println("Logic3");
    check(tasks);
    start(tasks);

    // Code Smell: Accumulator Loop: avoid using for to build a 'result' (collection/number)
    List<Integer> taskIds = tasks.stream().map(Task::getId).toList(); // easier to read

    if (cr323) { // TODO remove the boolean
      for (Task task : tasks) {
        System.out.println("My Logic: " + task);
      }
    }
    audit(tasks);
    System.out.println("Logic6 " + tasks.size());
    System.out.println("Task Ids: " + taskIds);
    System.out.println("Logic8");
  }

  private void audit(ImmutableList<Task> tasks) {
    int index = 0;
    for (Task task : tasks) {
      index++;
      System.out.println("Audit task #" + index + ": " + task);
    }
  }

  private void start(ImmutableList<Task> tasks) {
    for (Task task : tasks) {
      System.out.println("Starting " + task);
      task.setStarted(true);
    }
  }

  private void check(List<Task> tasks) {
//    tasks.remove(0); // surprise
  }
}
//Pratik was here.. can delete