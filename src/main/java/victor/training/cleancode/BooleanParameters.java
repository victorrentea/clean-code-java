package victor.training.cleancode;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class SomeController {
  SomeService someService;

  @GetMapping("blue/{storeId}")
  public void blueEndpoint(@PathVariable int storeId, @RequestBody Task task) {
    someService.blueMethod(storeId, task);
  }

  @GetMapping("red/{storeId}")
  public void redEndpoint(@PathVariable int storeId, @RequestBody Task task) {
    someService.redMethod(storeId, task);
  }
}

class SomeService {
  public void blueMethod(int id, Task task) {
    BooleanParameters.bigUglyMethod(id, task);
  }

  public void greenMethod(int id, Task task) {
    BooleanParameters.bigUglyMethod(id, task);
  }

  public void yellowMethod(int id, Task task) {
    BooleanParameters.bigUglyMethod(id, task);
  }

  public void redMethod(int id, Task task) {
    BooleanParameters.bigUglyMethod(id, task);
  }
}

class MyService {
  public void useCase323(int id, Task task) {
    // TODO The shared called method must execute logic specific for my use-case #323
    BooleanParameters.bigUglyMethod323(id, task);
  }
}

public class BooleanParameters {

  // Note: this method might be called from multiple places in the codebase ...
  public static void bigUglyMethod(int storeId, Task task) {
    cowThings(storeId, task);
    donkeyThings(storeId);
  }

  public static void bigUglyMethod323(int storeId, Task task) {
    cowThings(storeId, task);
    System.out.println("Logic just for CR#323 : " + task);
    donkeyThings(storeId);
  }

  private static void donkeyThings(int storeId) {
    System.out.println("Donkey Logic 1 " + storeId);
    System.out.println("Donkey Logic 2 " + storeId);
    System.out.println("Donkey Logic 3 " + storeId);
  }

  private static void cowThings(int storeId, Task task) {
    System.out.println("Cow Logic 1 " + task + " and " + storeId);
    System.out.println("Cow Logic 2 " + task);
    System.out.println("Cow Logic 3 " + task);
  }


  // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================

  private static void bossEnd(List<Task> tasks) {
    auditTasks(tasks);
    // you are afraid. why?
    // 1) operations must be done in the same order for a certain element: when doing step1 for all elements, then step2 for all
    //    produces different results than step1-step2 for each element in turn
    // 2) WORSE PERFORMANCE? NO! the compiler will optimize this for you. It will do the same thing as if you had done it in a single loop (almost)
    int tasksCount = tasks.size();
    System.out.println("Logic6 " + tasksCount);
    List<Integer> taskIds = tasks.stream().map(Task::getId).toList();
    System.out.println("Task Ids: " + taskIds);
    System.out.println("Logic8");
  }

  private static void bossStart(List<Task> tasks) {
    System.out.println("Logic1");
    System.out.println("Logic3");
    startTasks(tasks);
  }

  private static void auditTasks(List<Task> tasks) {
    int index = 0;
    for (Task task : tasks) {
      index++;
      System.out.println("Audit task #" + index + ": " + task);
    }
  }

  private static void startTasks(List<Task> tasks) {
    for (Task task : tasks) {
      System.out.println("Validate " + task);
      task.setStarted(true);
    }
  }

  // Lord gave us tests! 👌 TODO run tests
  public void bossLevelFluff(List<Task> tasks) {
    bossStart(tasks);
    bossEnd(tasks);
  }

  public void bossLevelFluff323(List<Task> tasks) {
    bossStart(tasks);
    for (Task task : tasks) {
      System.out.println("My Logic: " + task);
    }
    bossEnd(tasks);
  }

  public void bossLevelNoFluff(List<Task> tasks) {
    System.out.println("Logic1");
    System.out.println("Logic7 on fluff=false " + tasks);
    System.out.println("Logic8");
  }

  private void innocentMethod(List<Task> tasks) {
    tasks.clear(); // BAAAAD PRACTICE! 😱
  }

}


@Data
class Task {
  private final int id;
  private boolean started;
}