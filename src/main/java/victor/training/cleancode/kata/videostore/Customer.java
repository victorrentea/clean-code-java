package victor.training.cleancode.kata.videostore;

import java.util.LinkedHashMap;
import java.util.Map;

// Remove this comment (thi-cao)
class Customer {
  private final String name;
  private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order of elements

  public Customer(String name) {
    this.name = name;
  }

  private static double getThisAmount(PriceCode priceCode, int daysRented) {
    return switch (priceCode) {
      case REGULAR -> {
        double thisAmount = 2;
        if (daysRented > 2)
          thisAmount += (daysRented - 2) * 1.5;
        yield thisAmount;
      }
      case NEW_RELEASE -> daysRented * 3;
      case CHILDRENS -> {
        double thisAmount = 1.5;
        if (daysRented > 3)
          thisAmount += (daysRented - 3) * 1.5;
        yield thisAmount;
      }
//      default -> //bad practice
    };
  }

  public void addRental(Movie movie, int daysRented) {
    rentals.put(movie, daysRented);
  }

  public String getName() {
    return name;
  }

  public String statement() {
    double totalAmount = 0;
    int frequentRenterPoints = 0;
    String result = "Rental Record for " + getName() + "\n";
    // loop over each movie rental
    for (Movie movie : rentals.keySet()) {
      double currentMovieAmount = 0;
      // determine amounts for every line
      int daysRented = rentals.get(movie);
      currentMovieAmount = getThisAmount(movie.priceCode(), daysRented);

      // show figures line for this rental
      result += "\t" + movie.title() + "\t" + currentMovieAmount + "\n";
      totalAmount += currentMovieAmount;
    }
    frequentRenterPoints = getFrequentRenterPoints();

    // add footer lines
    result += "Amount owed is " + totalAmount + "\n";
    result += "You earned " + frequentRenterPoints + " frequent renter points";
    return result;
  }

  private int getFrequentRenterPoints() {
    return rentals.keySet().stream().mapToInt(movie -> {
      int daysRented = rentals.get(movie);
      return movie.priceCode() == PriceCode.NEW_RELEASE && daysRented > 1 ? 2 : 1;
    }).sum();
  }


}
