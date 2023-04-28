package victor.training.cleancode.optional.abuse;

public class OptionalParameters {

  public void callers() {
    // without
    sendMessage("jdoe", "message");

    // with
    sendTrackedMessage("jdoe", "message", "REGLISS");
  }

  // ⬇⬇⬇⬇⬇⬇ utility / library code ⬇⬇⬇⬇⬇⬇
  public void sendMessage(String recipient, String message) {
    System.out.println("Resolve phone number for " + recipient);
    System.out.println("Send message " + message);
  }

  public void sendTrackedMessage(String recipient, String message, String trackingRegistry) {
    sendMessage(recipient, message);
    System.out.println("Also notify the tracking registry : " + trackingRegistry);
  }
}
