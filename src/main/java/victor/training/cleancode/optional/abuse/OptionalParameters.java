package victor.training.cleancode.optional.abuse;

import java.util.Optional;

public class OptionalParameters {

  public void callers() {
    // without
    sendMessage("jdoe", "message");

    // with
    sendTrackedMessage("jdoe", "message", "REGLISS");
    sendTrackedMessage("jdoe", "message", "REGLISS");
    sendTrackedMessage("jdoe", "message", "REGLISS");
    sendTrackedMessage("jdoe", "message", "REGLISS");
//    sendMessage("jdoe", "message", null);

  }

  // ⬇⬇⬇⬇⬇⬇ utility / library code ⬇⬇⬇⬇⬇⬇
  public void sendTrackedMessage(String recipient, String message, String trackingRegistry) {
    sendMessage(recipient, message);
    System.out.println("Also notify the tracking registry : " + trackingRegistry);
    System.out.println("Also notify the tracking registry : " + trackingRegistry);
    System.out.println("Also notify the tracking registry : " + trackingRegistry);
  }
  public void sendMessage(String recipient, String message) {
    System.out.println("Resolve phone number for " + recipient);
    System.out.println("Send message " + message);
  }
}
record Message(String recipient, String message, Optional<String> trackingRegistry) {}
