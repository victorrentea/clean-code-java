package videostore.horror;

import static videostore.horror.Movie.PriceCode.NEW_RELEASE;

public record MovieRental(Movie movie, int daysRented) {

//  public String formatBodyLine() { // nu e SRP;  !
//      //1) e view concern:  tre sa stea langa footer si header. o sa alerg prin cod sa caut formatari de stringuri in multe locuri
//      // 2) dat fiind ca e f specific fluxului de statement, de ce-l pui intr-o clasa asa generica (model)
//      return "\t" + movie().title() + "\t" + calculateMoviePrice() + "\n";
//  }

  public int calculateRenterPoints() {
    int result = 1;
    boolean isNewRelease = movie().priceCode() == NEW_RELEASE;
    if (isNewRelease && daysRented() >= 2) {
      result++;
    }
    return result;
  }

  public double calculateMoviePrice() {
//    return movie.priceCode().calculatePrice(daysRented);
    return switch (movie().priceCode()) {
      case REGULAR -> regularPrice();
      case NEW_RELEASE -> newReleasePrice();
      case CHILDREN -> childrenPrice();
      case ELDERS -> 1;
    };

    // cu default: throw
//    switch (movie().priceCode()) {
//      case REGULAR:
//        return regularPrice();
//      case NEW_RELEASE:
//        return newReleasePrice();
//      case CHILDREN:
//        return childrenPrice();
//      default:
//        throw new IllegalStateException("Unexpected value: " + movie().priceCode());
//    }
  }

  private int newReleasePrice() {
    return daysRented * 3;
  }

  private double childrenPrice() {
    double result = 1.5;
    if (daysRented > 3)
      result += (daysRented - 3) * 1.5;
    return result;
  }

  private double regularPrice() {
    double result = 2;
    if (daysRented > 2)
      result += (daysRented - 2) * 1.5;
    return result;
  }
}