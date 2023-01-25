package victor.training.cleancode.optional.abuse;

import victor.training.cleancode.exception.model.MemberCard;
import victor.training.cleancode.optional.Optional_Intro.Discount;

import java.util.Optional;

public class OptionalParameters {

  public void sendMessage(String recipient, String message) {
    System.out.println("Resolve phone number for " + recipient);
  }
  public void sendMessageWithTracking(String recipient, String message, String trackingRegistry) {
    sendMessage(recipient, message);

    System.out.println("Also notify the tracking registry");
  }

  public void callers() {
    // with
    sendMessageWithTracking("jdoe", "message", "REGLISS");

    // without
    sendMessage("jdoe", "message");
  }
}
