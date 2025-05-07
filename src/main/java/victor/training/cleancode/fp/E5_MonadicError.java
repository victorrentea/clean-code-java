package victor.training.cleancode.fp;

public class E5_MonadicError {
  public Long tryingToDoStuff(String payload) {
    var request = process(payload);
    Long newId = dbInsert(request);
    kafkaSendAudit(request);
    return newId;
    // TODO plot twist: bulk processing
  }

  private String process(String payload) {
    if (payload == null || payload.isBlank()) {
      throw new IllegalArgumentException("Invalid payload");
    }
    if (payload.contains("Analytica")) {
      // FIXME: "It's not FP to throw exceptions!", the preacher said
      throw new IllegalArgumentException("Banned Business");
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
}
