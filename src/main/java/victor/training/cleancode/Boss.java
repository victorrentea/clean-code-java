package victor.training.cleancode;

import com.google.common.collect.ImmutableList;

import java.util.List;

// Deep nested functions are harder to break down
public class Boss {

  // 👌 TODO run tests
  public void bossLevel(ImmutableList<Task> tasks, boolean cr323) {
    if (tasks.isEmpty()) return;
    logic13();
    check(tasks); // when a method does not return you anything, that method must change stuff (or throw)
    start(tasks);
    if (cr323) { // TODO remove the boolean
      for (Task task : tasks) { // PR rejected: how DARE you use a for!!
        System.out.println("My Logic: " + task);
      }
    }
    audit(tasks);
    foo(tasks);
  }

  private void foo(ImmutableList<Task> tasks) {
    System.out.println("Logic6 " + tasks.size());
    List<Integer> taskIds = tasks.stream().map(Task::getId).toList();
    System.out.println("Task Ids: " + taskIds);
    System.out.println("Logic8");
  }

  private void logic13() {
    System.out.println("Logic1");
    System.out.println("Logic3");
  }

  private void audit(ImmutableList<Task> tasks) {
    int index = 0;
    for (Task task : tasks) { // PR rejected: how DARE you use a for!!
      index++;
      System.out.println("Audit task #" + index + ": " + task);
    }
  }

  private void start(ImmutableList<Task> tasks) {
    for (Task task : tasks) { // PR rejected: how DARE you use a for!!
      System.out.println("Starting " + task);
      task.setStarted(true);
    }
  }

  public void bossLevelForNotNL(ImmutableList<Task> tasks) {
    if (tasks.isEmpty()) return;
    System.out.println("Logic1");
    System.out.println("Logic7 on fluff=false " + tasks);
    System.out.println("Logic8");
  }

  private void check(ImmutableList<Task> tasks) {
    // Immutable List from Guava implements List but deprecated all the mutating methods
    // do not mix ImmutableList with Hibernate @Entity
    //tasks.remove(0); // surprise
  }
}
