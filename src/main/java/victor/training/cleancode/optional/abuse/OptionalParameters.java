package victor.training.cleancode.optional.abuse;

import victor.training.cleancode.exception.model.MemberCard;
import victor.training.cleancode.optional.Optional_Intro.Discount;

import java.util.Optional;

public class OptionalParameters {

  public void callers() {
    // without
    sendMessage("jdoe", "message", Optional.empty());

    // with
    sendMessage("jdoe", "message", Optional.of("REGLISS"));

  }

  // ⬇⬇⬇⬇⬇⬇ utility / library code ⬇⬇⬇⬇⬇⬇
  public void sendMessage(String recipient, String message, Optional<String> trackingRegistry) {
    System.out.println("Resolve phone number for " + recipient);
    System.out.println("Send message " + message);

    trackingRegistry.ifPresent(reg -> System.out.println("Also notify the tracking registry : " + reg));
  }
}
