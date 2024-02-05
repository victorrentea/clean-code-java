package victor.training.cleancode;

import com.google.common.collect.ImmutableList;

import java.util.List;

// Deep nested functions are harder to break down
public class Boss {

  // 👌 TODO run tests
  public void bossLevel(ImmutableList<Task> tasks) {
    if (tasks.isEmpty()) return;
    bossStart(tasks);
    bossEnd(tasks);
  }
  public void bossLevel323(ImmutableList<Task> tasks) {
    if (tasks.isEmpty()) return;
    bossStart(tasks);

    for (Task task : tasks) {
      System.out.println("My Logic: " + task);
    }
    bossEnd(tasks);
  }

  private void bossEnd(ImmutableList<Task> tasks) {
    auditTasks(tasks);
    System.out.println("Logic6 " + tasks.size());
    List<Integer> taskIds = tasks.stream().map(Task::getId).toList();
    // 17: better than .collect(Collectors.toList()) because it's immutable!
    // careful when refactoring to .toList that the users of that list don't change it.
    System.out.println("Task Ids: " + taskIds);
    System.out.println("Logic8");
  }

  private void auditTasks(ImmutableList<Task> tasks) {
    int index = 0;
    for (Task task : tasks) {
      // audit task
      index++;
      System.out.println("Audit task #" + index + ": " + task);
    }
  }

  private void bossStart(ImmutableList<Task> tasks) {
    System.out.println("Logic1");
    System.out.println("Logic3");
    check(tasks);
    // we learned that fors COST TIME : O(N), O(N^2), if nested on on a  large collection
    // > performance hit? only if your response time < 1 ms (eg 50 microsec), 1 DB hit = 5-10ms
    // > bugs because actions happen in the wrong order
    for (Task task : tasks) {
      System.out.println("Starting " + task);
      task.setStarted(true);
    }
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
