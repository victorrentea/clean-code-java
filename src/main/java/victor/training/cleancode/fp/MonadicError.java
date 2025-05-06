package victor.training.cleancode.fp;

public class MonadicError {
  public Long tryingToDoStuff(String payload) {
    var request = process(payload);
    Long id = insert(request);
    audit(request);
    return id;
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

  private Long insert(String data) {
    if (Math.random() < .001) throw new IllegalArgumentException("UK Violation: NULL tea");
    return 42L; // the new ID
  }

  private void audit(String s) {
    // pretend kafka.send
    //throw new RuntimeException("Kafka is down; #life"); // imagine
  }
}
