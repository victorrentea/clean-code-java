package victor.training.cleancode;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Deep nested functions are harder to break down
public class Boss {

  // 👌 TODO run tests
  public void bossLevel(ImmutableList<Task> tasks, boolean cr323) {
    if (tasks.isEmpty()) return;
    System.out.println("Logic1");
    System.out.println("Logic3");
    check(tasks);
    int i = 0;

    for (Task task : tasks) {
      System.out.println("Starting " + task);
      task.setStarted(true);
    }

    List<Integer> taskIds = tasks.stream()
        .map(Task::getId)
        .toList();

    for (Task task : tasks) {
      if (cr323) { // TODO remove the boolean
        System.out.println("My Logic: " + task);
      }
}
    for (Task task : tasks) {
      i++;
      System.out.println("Audit task #" + i + ": " + task);
    }
    System.out.println("Logic6 " + tasks.size());
    System.out.println("Task Ids: " + taskIds);
    System.out.println("Logic8");
  }

  public void bossLevelNonBg(ImmutableList<Task> tasks) {
    if (tasks.isEmpty()) return;
    System.out.println("Logic1");
    System.out.println("Logic7 on fluff=false " + tasks);
    System.out.println("Logic8");
  }

  // ImmutableList,-Set,-Map nu sunt compatibile cu ORM (JPA, Hibernate)
  private void check(ImmutableList<Task> tasks) {
//    List.of(1,2,3);
//    tasks.remove(0); // runtime error, dar cum datorita ImmutableList(guava) ai si deprecation warning
    // unde mutezi lista
  }
}
