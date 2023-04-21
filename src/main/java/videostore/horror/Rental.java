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
//    return movie.priceCode().getPrice(daysRented);

    return switch (movie.priceCode()) {
      case REGULAR -> getRegularPrice();
      case NEW_RELEASE -> getNewReleasePrice();
      case CHILDREN -> getChildrenPrice();
      case ELDER -> 0;
    };
  }
  // pre java 17
  //  switch (movie.priceCode()) {
  //      case REGULAR:
  //        return getRegularPrice();
  //      case NEW_RELEASE:
  //        return getNewReleasePrice();
  //      case CHILDREN:
  //        return getChildrenPrice();
  //      default:
  //        throw new IllegalStateException("Unexpected value: " + movie.priceCode());
  //    }


  private double getChildrenPrice() {
    double result =  1.5;
    if (daysRented > 3)
      result += (daysRented - 3) * 1.5;
    return result;
  }

  private int getNewReleasePrice() {
    return daysRented * 3;
  }

  private double getRegularPrice() {
    double result = 2;
    if (daysRented > 2)
      result += (daysRented - 2) * 1.5;
    return result;
  }
}
