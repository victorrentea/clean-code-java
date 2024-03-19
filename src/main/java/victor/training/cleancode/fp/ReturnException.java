package victor.training.cleancode.fp;

import io.vavr.control.Try;

public class ReturnException {
  // FP kung-fu
  public Try<Long> trying(Data data) {
    return process(data)
        .onSuccess(e -> System.out.println("Calling"))
        .flatMap(this::saveToDb)
        .andThen(id -> audit(data.input()));
  }

  // non-FP way
  public Try<Long> tryingNonFP(Data data) {
    Try<String> inputTry = process(data);
    if (inputTry.isFailure()) {
      return Try.failure(inputTry.getCause());
    }
    System.out.println("Calling");
    Try<Long> idTry =  inputTry.flatMap(this::saveToDb);
    if (idTry.isSuccess()) {
      audit(data.input());
    }
    return idTry;
  }

  private Try<String> process(Data data) {
    String input = data.input();
    if (input == null || input.isBlank()) {
      return Try.failure(new IllegalArgumentException("Invalid input."));
    }
    return Try.success(input.trim());
  }

  private Try<Long> saveToDb(String data) {
    return Try.success(42L);
  }

  private void audit(String s) {
    //throw new RuntimeException("LOL");
  }
}

record Data(String input) {

}
