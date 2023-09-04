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
    System.out.println("chestii");
    // TODO The shared called method must execute logic specific for my use-case #323
    BooleanParameters.bigUglyMethod323(id, task);
    System.out.println("chestii");
  }
}

public class BooleanParameters {

  // Warning⚠️: this method might be called from multiple places in the codebase ...
  public static void bigUglyMethod(int storeId, Task task) {
    bigStart(storeId, task);

    bigEnd(storeId);
  }

  public static void bigUglyMethod323(int storeId, Task task) {
    bigStart(storeId, task);
    System.out.println("Logic just for CR#323 : " + task);
    bigEnd(storeId);
  }

  private static void bigStart(int storeId, Task task) {
    System.out.println("Cow Logic 1 " + task + " and " + storeId);
    System.out.println(task);
    System.out.println("Cow Logic 3 " + task);
  }

  private static void bigEnd(int storeId) {
    System.out.println("Donkey Logic 1 " + storeId);
    System.out.println("Donkey Logic 2 " + storeId);
    System.out.println("Donkey Logic 3 " + storeId);
  }


  // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================

  // Lord gave us tests! 👌 TODO run the tests
  public void bossLevelFluff(List<Task> tasks) {
    bossStart(tasks);
    for (Task task : tasks) {
      System.out.println("My Logic: " + task);
    }
    bossEnd(tasks);
  }

  public void bossLevelFluff323(List<Task> tasks) {
    bossStart(tasks);
    for (Task task : tasks) {
      System.out.println("My Logic: " + task);
    }
    bossEnd(tasks);
  }

  private static void bossEnd(List<Task> tasks) {
    int index = 0;
    for (Task task : tasks) {
      index++;
      System.out.println("Audit task #" + index + ": " + task);
    }
    //CAND SPARGI UN FOR, LA CE TRE SA AI GRIJA?
    // 1) BUGURI
    // for(e) { A(e); B(e); } => for (e) A(e); for (e) B(e);
    // A1 B1 A2 B2 -> A1 A2 B1 B2 => daca efectul este diferit => BUG
    // 2) Peformanta NU va degrada daca faci macar 1 apel
    //   de networking catre alt sistem in use-caseul tau

    System.out.println("Logic6 " + tasks.size());
    // antipatterni in java 8+ sa faci list.add, map.put, total+= daca poti inloocui cu .stream()...
    List<Integer> taskIds = tasks.stream().map(Task::getId).toList(); // immutable❤️❤️❤️❤️❤️ list ❤️
    System.out.println("Task Ids: " + taskIds);
    System.out.println("Logic8");
  }

  private static void bossStart(List<Task> tasks) {
    System.out.println("Logic1");
    System.out.println("Logic3");
//    innocenta(tasks); // side effects : o fuctie intoarce void si ia o lista param. te intrebi: modifici lista ?

    for (Task task : tasks) {
      System.out.println("Starting " + task);
      task.setStarted(true);
    }
  }

  private void innocenta(List<Task> tasks) {
    tasks.clear();
  }


  public void bossLevelNoFluff(List<Task> tasks) {
    System.out.println("Logic1");
    System.out.println("Logic7 on fluff=false " + tasks);
    System.out.println("Logic8");
  }

}


@Data
class Task {
  private final int id;
  private boolean started;
}