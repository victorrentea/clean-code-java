package victor.training.cleancode.fp;

import java.util.List;

public class E5_MonadicError {
  public Long tryingToDoStuff(String payload) {
    var request = process(payload);
    Long newId = dbInsert(request);
    kafkaSendAudit(request);
    return newId;
  }

  private String process(String payload) {
    if (payload.isBlank()) {
      throw new IllegalArgumentException("Invalid payload");
    }
    if (payload.contains("Analytica")) {
      throw new IllegalArgumentException("Banned Business"); // FIXME: "not FP!", the preacher said
    }
    return payload.trim().toUpperCase();
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
