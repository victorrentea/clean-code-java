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

  // Warning⚠️: this method might be called from multiple places in the codebase ...
  public static void bigUglyMethod(int storeId, Task task) {
    cow(storeId, task);

    donkey(storeId);
  }

  public static void bigUglyMethod323(int storeId, Task task) {
    cow(storeId, task);
    System.out.println("Logic just for CR#323 : " + task);
    donkey(storeId);
  }

  private static void donkey(int storeId) {
    System.out.println("Donkey Logic 1 " + storeId);
    System.out.println("Donkey Logic 2 " + storeId);
    System.out.println("Donkey Logic 3 " + storeId);
  }

  private static void cow(int storeId, Task task) {
    System.out.println("Cow Logic 1 " + task + " and " + storeId);
    System.out.println(task);
    System.out.println("Cow Logic 3 " + task);
  }


  // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================

  // Lord gave us tests! 👌 TODO run the tests
  public void bossLevelFluff(List<Task> tasks) {
    bossStart(tasks);
    auditTasks(tasks);
    bossEnd(tasks);
  }

  public void bossLevelFluff323(List<Task> tasks) {
    bossStart(tasks);
    for (Task task : tasks) {
      System.out.println("My Logic: " + task);
    }
    auditTasks(tasks);
    bossEnd(tasks);
  }

  private static void bossEnd(List<Task> tasks) {
    int taskCount = tasks.size();
    System.out.println("Logic6 " + taskCount);
    List<Integer> taskIds = tasks.stream().map(Task::getId).collect(Collectors.toList());
    System.out.println("Task Ids: " + taskIds);
    System.out.println("Logic8");
  }

  private static void auditTasks(List<Task> tasks) {
    int index = 0;
    for (Task task : tasks) {
      index++; // mutable shared state !! yuuuuu 🤢
      System.out.println("Audit task #" + index + ": " + task); // B
    }
  }

  private static void bossStart(List<Task> tasks) {
    System.out.println("Logic1");
    // o functie 'adanca' cu multe taburi e greu de spart. => functiile sa fie plate!
    System.out.println("Logic3");
    for (Task task : tasks) {
      System.out.println("Starting " + task);
      task.setStarted(true);
    }
  }
  // Probleme cu 'Split Loop' Refactoring
  // - mai multe linii de cod
  // - performanta => de 4 x for => dc multe elemente eg 10k faci de prea multe ori JMP =>
  //  daca datele vin de peste retea (eg DB,API), deja ai stat muuuuuult timp sa le aduci
  //  relativ la asta, forul nu conteaza.
  // - !! Buguri: daca ordinea operatiilor conteaza: A1 B1 A2 B2 <> A1 A2 B1 B2

  public void bossLevelNoFluff(List<Task> tasks, boolean cr323) {
    System.out.println("Logic1");
    // o functie 'adanca' cu multe taburi e greu de spart. => functiile sa fie plate!
    System.out.println("Logic7 on fluff=false " + tasks);
    System.out.println("Logic8");
  }

  private double computeTax(List<Task> tasks) {
    tasks.clear();
    return 0;
  }

}


@Data
class Task {
  private final int id;
  private boolean started;
  //  private boolean cr323; // DOAMNE FERESTE !!!
}