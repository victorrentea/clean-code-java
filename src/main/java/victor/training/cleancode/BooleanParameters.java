package victor.training.cleancode;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

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
    BooleanParameters.bigUglyMethod(id, task, ()->{});
  }

  public void greenMethod(int id, Task task) {
    BooleanParameters.bigUglyMethod(id, task, ()->{});
  }

  public void yellowMethod(int id, Task task) {
    BooleanParameters.bigUglyMethod(id, task, ()->{});
  }

  public void redMethod(int id, Task task) {
    BooleanParameters.bigUglyMethod(id, task, ()->{});
  }
}

class MyService {
  public void useCase323(int id, Task task) {
    // TODO The shared called method must execute logic specific for my use-case #323
    BooleanParameters.bigUglyMethod(id, task, () -> System.out.println("Logic just for CR#323 : " + task));
  }
}

public class BooleanParameters {

  // BAZOOKA! foarte heavy solutia. Merita cand ai
  // - o gramada de cod arbitrar (5-8) in calleri pe care vrei sa-l executi in centru
  // - NU VREI SA PUI "Logic just for CR#323" in clasa. Vrei metoda de mai jos sa fie doar un
  //    host pentru logica altora.
  // - daca codul abitrar de executat trebuia rulat intr-un "try { lambda(); } catch"

  // Warning⚠️: this method might be called from multiple places in the codebase ...
  public static void bigUglyMethod(int storeId, Task task, Runnable lambda) {
    System.out.println("Cow Logic 1 " + task + " and " + storeId);
    System.out.println(task);
    System.out.println("Cow Logic 3 " + task);

    lambda.run();

    System.out.println("Donkey Logic 1 " + storeId);
    System.out.println("Donkey Logic 2 " + storeId);
    System.out.println("Donkey Logic 3 " + storeId);
  }


  // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================

  // Lord gave us tests! 👌 TODO run the tests
  public void bossLevelFluff(List<Task> tasks) {
    bossStart();
    startTasks(tasks);
    auditTasks(tasks);
    bossEnd(tasks);
  }
  public void bossLevelFluff323(List<Task> tasks) {
    bossStart();
    startTasks(tasks);
    myBo$$Logic(tasks);
    auditTasks(tasks);
    bossEnd(tasks);
  }
  // --- sub linie, doar low level details

  private static void myBo$$Logic(List<Task> tasks) {
    for (Task task : tasks) {
      System.out.println("My Logic: " + task);
    }
  }

  private static void bossEnd(List<Task> tasks) {
    System.out.println("Logic6 " + tasks.size());
    List<Integer> taskIds = tasks.stream().map(Task::getId).toList();
    System.out.println("Task Ids: " + taskIds);
    System.out.println("Logic8");
  }

  private static void auditTasks(List<Task> tasks) {
    int index = 0;
    for (Task task : tasks) {
      // audit
      index++;
      System.out.println("Audit task #" + index + ": " + task);
    }
  }

  private static void bossStart() {
    System.out.println("Logic1");
    System.out.println("Logic3");
    // cand iterarea de 2 ori ar putea deveni o problema de performanta ?
    // daca sunt milioane de elemente (tinute IN MEMORIE) in colectie si munca mea e ff rapida.
    // - daca datele au venit din DB/API calls, for x 10 => nici un impact.
    // - daca munca e grea per element, for x 10 => nici un impact
  }

  private static void startTasks(List<Task> tasks) {
    for (Task task : tasks) {
      System.out.println("Starting " + task);
      task.setStarted(true);
    }
  }


  public void bossLevelNoFluff(List<Task> tasks) {
    System.out.println("Logic1");
    System.out.println("Logic7 on fluff=false " + tasks);
    System.out.println("Logic8");
  }

  private void innocentFunction(List<Task> tasks) {
    tasks.clear();
  }

}


@Data
class Task {
  private final int id;
  private boolean started;
}