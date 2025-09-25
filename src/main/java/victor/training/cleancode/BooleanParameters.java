package victor.training.cleancode;

class SomeController {
  private SomeService someService;

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
    BooleanParameters.bigUglyMethod(id, task);
  }
}

public class BooleanParameters {

  // ⚠️ has multiple callers
  public static void bigUglyMethod(int storeId, Task task) {
    System.out.println("Donkey Logic 1 " + task + " and " + storeId);
    System.out.println(task);
    System.out.println("Donkey Logic 3 " + task);

    // System.out.println("Logic just for CR#323 : " + task);

    System.out.println("Sheep Logic 1 " + storeId);
    System.out.println("Sheep Logic 2 ");
    System.out.println("Sheep Logic 3 ");
  }
}


