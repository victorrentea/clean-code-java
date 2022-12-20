package victor.training.cleancode.fp;

import com.google.common.collect.ImmutableMap;
import lombok.Value;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class PureFunctionsIntro {

  public PureFunctionsIntro(ImmutableMap<Integer, Double> prices) {
    this.prices = prices;
  }

  public int avg(int a, int b) {
    return (a + b)/2;
  }

  public double stdev(List<Double> points) {
    double ssq = points.stream().mapToDouble(x -> x*x).average().orElse(0);
    return Math.sqrt(ssq);
  }

  private final ImmutableMap<Integer, Double> prices;
  private int priceHit = 0;
  public double getPrice(int productId) {
    priceHit ++;
    return prices.get(productId);
  }

  @Value
  static class Client {
    Long id;
    String name;
    Integer age;
    boolean gold;
  }
  public boolean isEligibleForAutoApprove(Client client, Set<Long> badPayer, double requestedLoan, Map<String, Object> flags) {
    if (badPayer.contains(client.id)) {
      return false;
    }
    if (client.isGold()) return true;
    if (requestedLoan < 10_000 && !(boolean)flags.getOrDefault("GAMBLER", false)) return true;
    if (requestedLoan < 30_000 && (boolean)flags.getOrDefault("REFINANCING", true)) return true;
    if (requestedLoan > 1_000_000) return false;
    return ((Double)flags.get("RISK_ASSESSMENT")) < 0.7;
  }

  public void validateCollect(Client client, List<String> errors) {
    if (client.getName() == null || client.getName().trim().length() < 3) {
      errors.add("Missing client name");
    }
    if (client.getAge() ==null || client.getAge() < 18) {
      errors.add("Underaged");
    }
  }

  public void validateThrowing(Client client) {
    if (client.getName() == null || client.getName().trim().length() < 3) {
      throw new IllegalArgumentException("Missing client name");
    }
    if (client.getAge() ==null || client.getAge() < 18) {
      throw new IllegalArgumentException("Underaged");
    }
  }
}
