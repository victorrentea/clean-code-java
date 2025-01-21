package victor.training.cleancode.kata.videostore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Customer {
  private final String name;
  private final List<Rental> rentals = new ArrayList<>();

  public Customer(String name) {
    this.name = name;
  }

  ;

  public void addRental(Movie movie, int days) {
    rentals.add(new Rental(movie, days));
  }

  public String getName() {
    return name;
  }

  public String statement() {
    return getHeader() + getBody() + getFooter();
  }

  private String getHeader() {
    return "Rental Record for " + name + "\n";
  }

  private String getBody() {
    return rentals.stream().map(this::getRentalDetails).collect(Collectors.joining());
  }

  private String getFooter() {
    double finalPrice = rentals.stream().mapToDouble(Rental::price).sum();
    int frequentRenterPoints = rentals.stream().mapToInt(this::getFrequentRenterPoints).sum();
    return "Amount owed is " + finalPrice + "\n"
           + "You earned " + frequentRenterPoints + " frequent renter points";
  }

  private String getRentalDetails(Rental rental) {
    return "\t" + rental.movie().title() + "\t" + rental.price() + "\n";
  }

  private int getFrequentRenterPoints(Rental rental) {
    int frequentRenterPoints = 1;
    if (rental.movieType() == MovieType.NEW_RELEASE && rental.days() > 1)
      frequentRenterPoints++;
    return frequentRenterPoints;
  }

}