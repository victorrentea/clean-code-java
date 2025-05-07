package victor.training.cleancode.fp;

import io.vavr.Tuple;
import io.vavr.control.Try;

import java.util.List;
import java.util.stream.Stream;

public class E5_MonadicError {
  public List<Long> tryingToDoStuff(Stream<String> payloads) {

    var tries = payloads.map(payload -> process(payload)
            .mapTry(r -> Tuple.of(r, dbInsert(r)))
            .andThen(t -> kafkaSendAudit(t._1))
            .map(t -> t._2))
        .toList();
    var errors = tries.stream()
        .filter(Try::isFailure)
        .map(Try::getCause)
        .toList();
    System.err.println(errors);

    var successfulIds = tries.stream()
        .filter(Try::isSuccess)
        .map(Try::get)
        .toList();
    return successfulIds;
  }

  private Try<String> process(String payload) {
    if (payload.isBlank()) {
      return Try.failure(new IllegalArgumentException("Invalid payload"));
    }
    if (payload.contains("Analytica")) {
      return Try.failure(new IllegalArgumentException("Banned Business")); // FIXME: "not FP!", preacher's PR Comment
    }
    return Try.success(payload.trim().toUpperCase());
  }

  private Long dbInsert(String data) {
    if (Math.random() < .001) throw new IllegalArgumentException("UK Violation: NULL tea");
    return 42L;
  }

  private void kafkaSendAudit(String message) {
    //throw new RuntimeException("Kafka is down; #life"); // imagine
  }

  private List<Long> dbInsertAll(List<String> data) {
    return List.of(42L);
  }
}
