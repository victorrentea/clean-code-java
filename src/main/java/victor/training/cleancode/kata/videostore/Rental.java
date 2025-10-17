package victor.training.cleancode.kata.videostore;

public record Rental (Movie movie, int daysRented) {

    double getCost() {
        double movieCost = 0;
        switch (movie.priceCode()) {
            case REGULAR:
                movieCost += 2;
                if (daysRented > 2)
                    movieCost += (daysRented - 2) * 1.5;
                break;
            case NEW_RELEASE:
                movieCost += daysRented * 3;
                break;
            case CHILDRENS:
                movieCost += 1.5;
                if (daysRented > 3)
                    movieCost += (daysRented - 3) * 1.5;
                break;
        }
        return movieCost;
    }

    int calculateRewardPoints() {
        if (movie.priceCode() == PriceCode.NEW_RELEASE && daysRented > 1) {
          return 2;
        } else {
          return 1;
        }
    }

  String generateStatementLine() {
    return "\t" + movie().title() + "\t" + getCost() + "\n";
  }
}
