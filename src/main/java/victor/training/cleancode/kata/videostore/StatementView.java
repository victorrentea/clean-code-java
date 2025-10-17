package victor.training.cleancode.kata.videostore;

import java.util.ArrayList;
import java.util.List;

public class StatementView {

  private final List<Rental> rentals = new ArrayList<>();

  public void addRental(Rental rental) {
    rentals.add(rental);
  }

  private int computeRewardPoints() {
    return rentals.stream().mapToInt(Rental::calculateRewardPoints).sum();
  }

  private double computeTotalCost() {
    return rentals.stream().mapToDouble(Rental::getCost).sum();
  }

  public String generateStatementBody() {
    int rewardPoints = computeRewardPoints();
    double totalCost = computeTotalCost();

    StringBuilder statementBody = new StringBuilder();
    for (Rental rental : rentals) {
      statementBody.append(rental.generateStatementLine());
    }

    statementBody.append(formatFooter(totalCost, rewardPoints));

    return statementBody.toString();
  }

  private static String formatFooter(double totalCost, int rewardPoints) {
    return "Amount owed is " + totalCost + "\n" +
           "You earned " + rewardPoints + " frequent renter points";
  }

}
