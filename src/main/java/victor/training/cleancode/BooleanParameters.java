package victor.training.cleancode;

import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * Implement the variation required for CR323 without adding a boolean parameter
 */
public class BooleanParameters {

  public static final String SPACE = " ";

  public static void main(String[] args) {
    // The big method is called from various foreign places in the codebase
    bigUglyMethod(1, new Task(5));
    bigUglyMethod(2, new Task(4));
    bigUglyMethod(3, new Task(3));
    bigUglyMethod(4, new Task(2));
    bigUglyMethod(5, new Task(1));

    // TODO From my use-case #323, I call it too, to do more within:
    Task task = new Task(1);
    bigUglyMethod323(2, task);

  }

  //      if (cr435) {
  //         System.out.println("Doar pentur uc maria cr#435");
  //      }


  static void bigUglyMethod(int b, Task task) {
    bigStart(b, task);
    sendNotificationTo3rdParties(b);
  }

  static void bigUglyMethod323(int b, Task task) {
    bigStart(b, task);

    System.out.println(SPACE + task);

    sendNotificationTo3rdParties(b);
  }

  private static void sendNotificationTo3rdParties(int b) {
    System.out.println("More Complex Logic " + b);
    System.out.println(SPACE + b);

    System.out.println("More Complex Logic " + b);
  }

  private static void bigStart(int b, Task task) {
    System.out.println("Complex Logic 1 " + task + " and " + b);
    System.out.println(SPACE + task);
    System.out.println("Complex Logic 3 " + task);
  }


  // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================

  // Lord gave us tests!
  public void bossLevelFluff(ImmutableList<Task> tasks) {
    bossStart(tasks);
    bossEnd(tasks);
  }
  public void bossLevelFluff323(ImmutableList<Task> tasks) {
    bossStart(tasks);
    for (Task task : tasks) {
      System.out.println(SPACE + task);
    }
    bossEnd(tasks);
  }

  private static void bossEnd(ImmutableList<Task> tasks) {
    int index = 0;
    for (Task task : tasks) {
      index++;
      System.out.println("Audit task index=" + index + ": " + task);
    }
    // de ce ne sperie cand in loc de 1 for fac 4 ?
    // - (fals) eficienta ?? => 4x O(N) = O(N)
    // - (bug) ce face un for in AFARA influenteaza ce faci in urmatorul for.
    //   <== bad practice, asta merge impotriva princ de Func Program

    int j = tasks.size();
    System.out.println("Logic6 " + j);
    List<Integer> taskIds = tasks.stream().map(Task::getId).toList();
    System.out.println("Task Ids: " + taskIds);
    System.out.println("Logic7");
  }

  private static void bossStart(ImmutableList<Task> tasks) {
    System.out.println("Logic2");
    System.out.println("Logic3");

    for (Task task : tasks) {
      System.out.println("Logic4: Validate " + task);
      task.setStarted();
    }
  }


  public void bossLevelNoFluff(ImmutableList<Task> tasks) {
    System.out.println("Logic2");
    System.out.println("Logic7 " + tasks);
    System.out.println("Logic7");
  }

  private int getClientActivesAndClearTasks(ImmutableList<Task> tasks) {
    //      tasks.clear();
    return 0;
  }

}


class Task {
  private final int id;
  private boolean started;

  Task(int id) {
    this.id = id;
  }

  public void setStarted() {
    started = true;
  }

  public boolean isStarted() {
    return started;
  }

  public int getId() {
    return id;
  }

  @Override
  public String toString() {
    return "Task{" + "id=" + id + ", started=" + started + '}';
  }
}