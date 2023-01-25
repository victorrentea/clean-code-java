package victor.training.cleancode;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Implement the variation required for CR323 without adding a boolean parameter
 */
public class BooleanParameters {
  @GetMapping("some/url")
  public void pretendEndpoint() {
    serviceMethod(2, new Task(4));
  }


  @GetMapping
  private void f2() {
    extracted();
  }

  private static void extracted() {
    bigUglyMethod(1, new Task(5));
  }


  private static void serviceMethod(int id, Task taskPotSiEuShiftF6) {
    bigUglyMethod(id, taskPotSiEuShiftF6);
  }

  public static void main(String[] argsnuPoateSaCrape) {
    // The big method is called from various foreign places in the codebase
    bigUglyMethod(3, new Task(3));
    bigUglyMethod(4, new Task(2));
    bigUglyMethod(5, new Task(1));



    // TODO From my use-case #323, I call it too, to do more within:
    Task task = new Task(1);
    bigUglyMethodCR323(2, task);
  }


  static void bigUglyMethod(int id, Task task) {
    complexLogic(id, task);
    moreComplexLogic(id);
  }

  // dar asta nu inseamna ca duplicam cod?
  // daca am 11 linii de cod si trebuie inca una dupa 6 din ele.
  // #1) primele5Linii() <- faci o metoda cu un nume stupid pentru ca cele 5 linii
  //    de cod nu AU DEFAPT nimic clar sub care sa le poti grupa
  // #2) cele11Linii(boolean flag)

  // !! ambele-s naspa, dar mai bune decat sa repeti 11 linii de cod =>
  //    DRY =  nu repeti aceeasi LOGICA in mai multe locuri in proj

  static void bigUglyMethodCR323(int id, Task task) {
    complexLogic(id, task);
    System.out.println("Logic just for CR#323 : " + task);
    moreComplexLogic(id);
  }

  private static void moreComplexLogic(int id) {
    System.out.println("More Complex Logic " + id);
    System.out.println("More Complex Logic " + id);
    System.out.println("More Complex Logic " + id);
  }

  private static void complexLogic(int id, Task task) {
    System.out.println("Complex Logic 1 " + task + " and " + id);
    System.out.println("Complex Logic 2 " + task);
    System.out.println("Complex Logic 3 " + task);
  }


  // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================

  // Lord gave us tests!
  public void bossLevel(boolean fluff, List<Task> tasks, boolean cr323) {
    System.out.println("Logic1");
    System.out.println("Logic2");
    if (fluff) {
      System.out.println("Logic3");
      int index = 0;
      for (Task task : tasks) {
        System.out.println("Logic4: Validate " + task);
        task.setStarted();
      }

      List<Integer> taskIds = tasks.stream().map(Task::getId).collect(toList());

      for (Task task : tasks) {
        if (cr323) { // TODO remove the boolean
          System.out.println("My Logic: " + task);
        }
      }
      for (Task task : tasks) {
        index++;
        System.out.println("Audit task index=" + index + ": " + task);
      }
      System.out.println("Logic6 " + tasks.size());
      System.out.println("Task Ids: " + taskIds);
    } else {
      System.out.println("Logic7 " + tasks);
    }
    System.out.println("Logic7");
  }

  private void checkTasks(List<Task> tasks) {
    tasks.removeIf(t -> t.getId() < 0);
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