package victor.training.cleancode.fp;

import io.vavr.Tuple2;
import io.vavr.control.Try;


public class MonadicErrorEnd {
  public Try<Long> tryingToDoStuff(String payload) {
    return process(payload)
        .flatMap(data -> insert(data).map(id -> new Tuple2<>(data, id))
            .andThen(s -> audit(s._1))
            .map(Tuple2::_2));
  }

  private Try<String> process(String payload) {
    if (payload == null || payload.isBlank()) {
      return Try.failure(new IllegalArgumentException("Invalid payload"));
    }
    if (payload.contains("Analytica")) {
      // FIXME: "It's not FP to throw exceptions!", the preacher said
      return Try.failure(new IllegalArgumentException("Banned Business"));
    }
    return Try.success(payload.trim().toUpperCase());
  }

  private Try<Long> insert(String data) {
    return Try.of(() -> {
      if (Math.random() < .001) throw new IllegalArgumentException("UK Violation: NULL tea");
      return 42L; // the new ID
    });
  }

  private void audit(String s) {
    // pretend kafka.send
    //throw new RuntimeException("Kafka is down; #life"); // imagine
  }
}

