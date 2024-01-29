package victor.training.cleancode;

import java.util.List;

// "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================
public class Boss {

  // Lord gave us tests! 👌 TODO run them
  public void bossLevel(List<Task> tasks, boolean cr323) {
    System.out.println("Logic1");
    System.out.println("Logic7 on fluff=false " + tasks);
    System.out.println("Logic8");
  }

  public void bossLevelScotland(List<Task> tasks) {
    startTasks(tasks);
    auditTasks(tasks);
  }
  public void bossLevelScotland323(List<Task> tasks) {
    startTasks(tasks);
    for (Task task : tasks) {
      System.out.println("My Logic: " + task);
    }
    auditTasks(tasks);
  }

  private void startTasks(List<Task> tasks) {
    System.out.println("Logic1");
    System.out.println("Logic3");

    for (Task task : tasks) {
      System.out.println("Starting " + task);
      task.setStarted(true);
    }
  }

  private void auditTasks(List<Task> tasks) {
    int index = 0;
    List<Integer> taskIds = tasks.stream().map(Task::getId).toList(); // accumulator = code smell since Java 8

    for (Task task : tasks) {
      index++;
      System.out.println("Audit task #" + index + ": " + task);
    }
    System.out.println("Logic6 " + tasks.size());
    System.out.println("Task Ids: " + taskIds);
    System.out.println("Logic8");
  }

  private void execute(List<Task> tasks) {
//    tasks.remove(0);// dirty Fri evening hack! < DON'T DO THIS!! you you still want a job.
    // DON'T MODIFY THE INPUT PARAMETER unless the method name explicitly says so
  }

}
