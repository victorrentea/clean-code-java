package videostore.horror;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Customer {
  private final String name;
  private final List<RentalMovie> rentals = new ArrayList<>(); // preserves order

  public Customer(String name) {
    this.name = name;
  }

  public void addRental(Movie movie, int rentalDays) {
    rentals.add(new RentalMovie(movie, rentalDays));
  }

  public String getName() {
    return name;
  }

  public String statement() {
    return generateHeader() + generateBody() + generateFooter();
  }
//  fun statement() = generateHeader() + generateBody() + generateFooter()

  private String generateBody() {
    return rentals.stream().map(this::generateFigures).collect(Collectors.joining());
  }

  private String generateHeader() {
    return "Rental Record for " + name + System.lineSeparator();
  }

  private String generateFooter() {
    return "Amount owed is " + calculateTotalPrice() + System.lineSeparator() +
           "You earned " + calculateTotalRenterPoints() + " frequent renter points";
  }

  private double calculateTotalPrice() {
    return rentals.stream().mapToDouble(RentalMovie::calculateMoviePrice).sum();
  }

  private int calculateTotalRenterPoints() {
    return rentals.stream().mapToInt(RentalMovie::calculateRenterPoints).sum();
  }

  private String generateFigures(RentalMovie rentedMovie) {
    return "\t" + rentedMovie.getMovie().getTitle() + "\t" + rentedMovie.calculateMoviePrice() + System.lineSeparator();
  }

}
