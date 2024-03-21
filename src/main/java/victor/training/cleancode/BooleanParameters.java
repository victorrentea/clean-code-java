package victor.training.cleancode;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

class SomeController {
  SomeService someService;

//  @GetMapping("blue/{storeId}")
  public void blueEndpoint(int storeId, Task task) {
    someService.blueMethod(storeId, task);
  }

//  @GetMapping("red/{storeId}")
  public void redEndpoint( int storeId,  Task task) {
    someService.redMethod(storeId, task);
  }
}

class SomeService {
  public void blueMethod(int id, Task task) {
    BooleanParameters.bigUglyMethod(id, task);
  }

  public void greenMethod(int id, Task task) {
    BooleanParameters.bigUglyMethod(id, task, t->{});
  }

  public void yellowMethod(int id, Task task) {
    BooleanParameters.bigUglyMethod(id, task, t->{});
  }

  public void redMethod(int id, Task task) {
    BooleanParameters.bigUglyMethod(id, task, t->{});
  }
}

class MyService {
  public void useCase323(int id, Task task) {
    // TODO The shared called method must execute logic specific for my use-case #323
    BooleanParameters.bigUglyMethod(id, task, t -> {
      System.out.println("Logic just for CR#323 : " + t);
    });
  }
}

public class BooleanParameters {

  // Warning⚠️: this method might be called from multiple places in the codebase ...
  public static void bigUglyMethod(int storeId, Task task, Consumer<Task> omg) {
    System.out.println("Donkey Logic 1 " + task + " and " + storeId);
    System.out.println(task);
    System.out.println("Donkey Logic 3 " + task);

//    System.out.println("Logic just for CR#323 : " + task);
    omg.accept(task); // over engineering, but fun !

    System.out.println("Sheep Logic 1 " + storeId);
    System.out.println("Sheep Logic 2 " + storeId);
    System.out.println("Sheep Logic 3 " + storeId);
  }


}


