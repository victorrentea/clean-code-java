package victor.training.cleancode;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

class SomeController {
  SomeService someService;

  @GetMapping("blue/{storeId}")
  public void blueEndpoint(@PathVariable int storeId, @RequestBody Task task) {
    someService.blueMethod(storeId, task);
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
    // TODO From my use-case #323, I call the method too, but have it do more within:
    BooleanParameters.bigUglyMethod323(2, task);
  }
}

public class BooleanParameters {

  // Note: this method might be called from multiple places in the codebase ...
  static void bigUglyMethod(int storeId, Task task) {
    cow(storeId, task);
    donkey(storeId);
  }

  static void bigUglyMethod323(int storeId, Task task) { // SRP
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
    System.out.println("Cow Logic 2 " + task);
    System.out.println("Cow Logic 3 " + task);
  }


  //        fix(tasks); // rau, da nu IAD
  //        int points = getPoints(tasks); // IAD // o metoda care dupa nume pare ca doar iti niste rezultate
  // nu se asteapta nimeni sa modifice date SOC si BUGURI
  // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================

  // Lord gave us tests!
  public void bossLevel(List<Task> tasks) {
    bossStart(tasks);
    bossEnd(tasks);
  }
  public void bossLevel323(List<Task> tasks) {
    bossStart(tasks);
    tasks.forEach(BooleanParameters::sendToKafka);
    bossEnd(tasks);
  }

  private static void sendToKafka(Task task) {
    System.out.println("My Logic: " + task);
  }

  private static void bossEnd(List<Task> tasks) {
    int index = 0;
    for (Task task : tasks) {
      index++;
      System.out.println("Audit task #" + index + ": " + task);
    }

    System.out.println("Logic6 " + tasks.size());
    List<Integer> taskIds = tasks.stream().map(Task::getId).toList();
    System.out.println("Task Ids: " + taskIds);
    System.out.println("Logic8");
  }

  private static void bossStart(List<Task> tasks) {
    System.out.println("Logic1");
    System.out.println("Logic3");
    for (Task task : tasks) {
      System.out.println("Validate " + task);
      task.setStarted();
    }
  }

  // Lord gave us tests!
  public void bossLevelNoFluff(List<Task> tasks) {
    System.out.println("Logic1");
    System.out.println("Logic7 on fluff=false " + tasks);
    System.out.println("Logic8");
  }

  private int getPoints(List<Task> tasks) {
    tasks.clear();
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