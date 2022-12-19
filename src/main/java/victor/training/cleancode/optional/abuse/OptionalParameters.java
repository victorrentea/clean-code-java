package victor.training.cleancode.optional.abuse;

import victor.training.cleancode.exception.model.MemberCard;
import victor.training.cleancode.optional.Optional_Intro.Discount;

import java.util.Optional;

public class OptionalParameters {

  public void sendMessage(String recipient, String message, Optional<String> trackingRegistry) {
    System.out.println("Resolve phone number for " + recipient);

    trackingRegistry.ifPresent(reg -> System.out.println("Also notify the tracking registry"));
  }

  public void callers() {
    // with
    sendMessage("jdoe", "message", Optional.of("REGLISS"));

    // without
    sendMessage("jdoe", "message", Optional.empty());
  }
}
