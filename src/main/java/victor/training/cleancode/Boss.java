package victor.training.cleancode;

import com.google.common.collect.ImmutableList;

import java.util.List;

// Deep nested functions are harder to break down
public class Boss {

  // 👌 TODO run tests
  public void bossLevel(ImmutableList<Task> tasks) {
    if (tasks.isEmpty()) {
      return;
    }
    System.out.println("Logic1");
    System.out.println("Logic7 on fluff=false " + tasks);
    System.out.println("Logic8");
  }

  public void bossLevelBulgaria(ImmutableList<Task> tasks, boolean cr323) {
    if (tasks.isEmpty()) {
      return;
    }
    start(tasks);

//    List<Integer> taskIds = tasks.stream().map(task -> task.getId()).collect(Collectors.toList());
//    List<Integer> taskIds = tasks.stream().map(task -> task.getId()).toList(); //java17 💖 lista intoarsa e readonly
//    List<Integer> taskIds = tasks.stream().map(Task::getId).toList(); // Etapa: dragoste de ::

    if (cr323) { // TODO remove the boolean
      for (Task task : tasks) {
        System.out.println("My Logic: " + task);
      }
    }
    end(tasks);
  }

  private void end(ImmutableList<Task> tasks) {
    List<Integer> taskIds = tasks.stream().map(Task::getId).toList();
    int index = 0;
    for (Task task : tasks) {
      index++;
      System.out.println("Audit task #" + index + ": " + task);
    }
    System.out.println("Logic6 " + tasks.size());
    System.out.println("Task Ids: " + taskIds);
    System.out.println("Logic8");
  }

  private void start(ImmutableList<Task> tasks) {
    System.out.println("Logic1");
    System.out.println("Logic3");
    check(tasks); // nu-mi da nimic inapoi = COMMAND method care arunca exceptie sau  face side-effects (modifica parametrii, INSERT, SEND, POST)
    startTasks(tasks);
  }

  private void startTasks(ImmutableList<Task> tasks) {
    for (Task task : tasks) {
      System.out.println("Starting " + task);
      task.setStarted(true);
    }
  }

  private void check(List<Task> tasks) {
//    tasks.remove(0); // surprise
  }
}
