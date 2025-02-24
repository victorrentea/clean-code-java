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
      result += "\t" + movieRental.movie().title() + "\t" + computeAmount( movieRental ) + "\n";
    }

    double totalAmount = rentals.stream().mapToDouble( Customer::computeAmount ).sum();

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

  private static double computeAmount( MovieRental movieRental )
  {
    double amount = 0;
    int daysRented = movieRental.daysRented();

    switch ( movieRental.movie().movieType() )
    {
      case REGULAR:
        amount += 2;
        if ( daysRented > 2 )
        {
          amount += ( daysRented - 2 ) * 1.5;
        }
        return amount;
      case NEW_RELEASE:
        amount += daysRented * 3;
        return amount;
      case CHILDREN:
        amount += 1.5;
        if ( daysRented > 3 )
        {
          amount += ( daysRented - 3 ) * 1.5;
        }
        return amount;
    }

    return amount;
  }

  private static boolean isEligibleForBonus( MovieRental movieRental )
  {
    return ( movieRental.movie().movieType() == NEW_RELEASE ) && movieRental.daysRented() > 1;
  }
}
