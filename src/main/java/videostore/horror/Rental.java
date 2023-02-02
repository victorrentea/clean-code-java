package videostore.horror;

import lombok.NonNull;

public class Rental {

  @NonNull
  private Movie movie;
  private int days;

  public Rental(@NonNull Movie movie, int days) {
    this.movie = movie;
    this.days = days;
  }

  double getPrice() {
//    return movie.priceCode().computePrice(days);

    // java 11
//    switch (getMovie().priceCode()) {
//      case REGULAR:
//        return getRegularPrice();
//      case NEW_RELEASE:
//        return getDays() * 3;
//      case CHILDREN:
//        return getChildrenPrice();
//      default:
//        throw new IllegalArgumentException("Riscant!");
//    }
    // java 17
    return switch (getMovie().priceCode()) {
      case REGULAR -> getRegularPrice();
      case NEW_RELEASE -> getDays() * 3;
      case CHILDREN -> getChildrenPrice();
    };
  }

  private double getChildrenPrice() {
    double price = 1.5;
    if (getDays() > 3)
      price += (getDays() - 3) * 1.5;
    return price;
  }

  private double getRegularPrice() {
    double price = 2;
    if (getDays() > 2)
      price += (getDays() - 2) * 1.5;
    return price;
  }

  int getFrequentRenterPoints() {
    boolean isEligibleForBonus = getMovie().priceCode() == PriceCode.NEW_RELEASE && getDays() >= 2;
    if (isEligibleForBonus) {
      return 2;
    }
    return 1;
  }

  public Movie getMovie() {
    return movie;
  }

  public int getDays() {
    return days;
  }
}
