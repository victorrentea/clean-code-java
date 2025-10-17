package victor.training.cleancode.kata.videostore;

import java.util.ArrayList;
import java.util.List;

public class Rentals {

  private final List<Rental> rentals = new ArrayList<>();

  public void addRental(Rental rental) {
    rentals.add(rental);
  }

  private int computeRewardPoints() {
    return rentals.stream().mapToInt(Rental::calculateRewardPoints).sum();
  }

  private double computeTotalCost() {
    return rentals.stream().mapToDouble(r -> r.movie().getCost(r.daysRented())).sum();
  }

  public String generateStatementBody() {
    int rewardPoints = computeRewardPoints();
    double totalCost = computeTotalCost();

    StringBuilder statementBody = new StringBuilder();
    for (Rental rental : rentals) {
      statementBody.append(rental.generateStatementLine());
    }

    appendFooter(statementBody, totalCost, rewardPoints);

    return statementBody.toString();
  }

  private static void appendFooter(StringBuilder statement, double totalCost, int rewardPoints) {
    statement.append("Amount owed is ").append(totalCost).append("\n");
    statement.append("You earned ").append(rewardPoints).append(" frequent renter points");
  }

}
