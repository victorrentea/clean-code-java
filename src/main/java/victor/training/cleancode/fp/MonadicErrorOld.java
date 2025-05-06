package victor.training.cleancode.fp;

import io.vavr.control.Try;
import victor.training.cleancode.fp.support.Payload;


public class MonadicErrorOld {
  // FP kung-fu
  public Try<Long> trying(Payload payload) {
    return process(payload)
        .onSuccess(e -> System.out.println("Calling"))
        .flatMap(this::saveToDb)
        .andThen(id -> audit(payload.input()));
  }

  // non-FP way
  public Try<Long> tryingNonFP(Payload payload) {
    Try<String> inputTry = process(payload);
    if (inputTry.isFailure()) {
      return Try.failure(inputTry.getCause());
    }
    System.out.println("Calling");
    Try<Long> idTry =  inputTry.flatMap(this::saveToDb);
    if (idTry.isSuccess()) {
      audit(payload.input());
    }
    return idTry;
  }

  // the author decided to return a Try<String> instead of a String + exception
  private Try<String> process(Payload payload) {
    String input = payload.input();
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

