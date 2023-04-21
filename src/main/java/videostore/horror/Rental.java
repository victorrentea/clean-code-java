package videostore.horror;

public record Rental(Movie movie, int daysRented) {
  // too cryptic, and too far from what the requirements said.
//  public int getFrequentRenterPoints() {
//    return movie.eligibleForBonus(daysRented) ? 2 : 1;
//  }

//  fun getFrequentRenterPoints() =
//      if (movie.eligibleForBonus(daysRented)) 2 else 1


  public int getFrequentRenterPoints() {
    int result = 1;
    if (movie.eligibleForBonus(daysRented)) {
      result ++;
    }
    return result;
  }

  public double getPrice() {
    double result = 0;
    switch (movie.priceCode()) {
      case REGULAR:
        result += 2;
        if (daysRented > 2)
          result += (daysRented - 2) * 1.5;
        break;
      case NEW_RELEASE:
        result += daysRented * 3;
        break;
      case CHILDREN:
        result += 1.5;
        if (daysRented > 3)
          result += (daysRented - 3) * 1.5;
        break;
    }
    return result;
  }
}
