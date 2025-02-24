package victor.training.cleancode.kata.videostore;

import java.util.ArrayList;
import java.util.List;

import static victor.training.cleancode.kata.videostore.MovieType.NEW_RELEASE;

class Customer
{
  private final String name;
  private final List<MovieRental> rentals = new ArrayList<>();

  public Customer( String name )
  {
    this.name = name;
  }

  public void addRental( Movie movie, int daysRented )
  {
    rentals.add( new MovieRental( movie, daysRented ) );
  }

  public String prepareRentalRecord()
  {
    String result = "Rental Record for " + name + "\n";

    int frequentRenterPoints = getFrequentRenterPoints();

    // Calculate total amount
    for ( MovieRental movieRental : rentals )
    {
      result += "\t" + movieRental.movie().title() + "\t" + movieRental.computeAmount() + "\n";
    }

    double totalAmount = rentals.stream().mapToDouble( MovieRental::computeAmount ).sum();

    // add footer lines
    result += "Amount owed is " + totalAmount + "\n";
    result += "You earned " + frequentRenterPoints + " frequent renter points";
    return result;
  }

  private int getFrequentRenterPoints( )
  {
    long count = rentals.stream()
      .map( Customer::isEligibleForBonus )
      .filter( r -> r )
      .count();

    return ( int ) count + rentals.size();
  }

  private static boolean isEligibleForBonus( MovieRental movieRental )
  {
    return ( movieRental.movie().movieType() == NEW_RELEASE ) && movieRental.daysRented() > 1;
  }
}
