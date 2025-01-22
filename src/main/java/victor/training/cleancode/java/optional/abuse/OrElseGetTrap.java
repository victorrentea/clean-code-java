package victor.training.cleancode.java.optional.abuse;

import java.util.Optional;

public class OrElseGetTrap {
  private record Transfer(String from, String to, int amount) {
  }

  static Optional<Transfer> takeOwnMoney(String user) {
    System.out.println("Taking money from " + user);
    return Optional.of(new Transfer("Victor", "Vodafone", 100));
  }

  static Transfer takeCreditMoney(String user) {
    System.out.println("Borrowing⚠️ money for " + user);
    return new Transfer("Creditor", "Vodafone", 100);
  }

  public static void main(String[] args) {
    Transfer transfer = takeOwnMoney("user")
        .orElse(takeCreditMoney("user"));
    System.out.println("Transfer: " + transfer);
  }
}
