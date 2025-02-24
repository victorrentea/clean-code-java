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
    StringBuilder result = new StringBuilder( "Rental Record for " + name + "\n" );

    rentals.forEach( mr -> result.append( generateResult( mr ) ) );

    return result.append( "Amount owed is " )
			.append( rentals.stream().mapToDouble( MovieRental::computeAmount ).sum() )
      .append( "\n" )
			.append( "You earned " )
			.append( getFrequentRenterPoints() )
			.append( " frequent renter points" )
			.toString();
  }

  private static String generateResult( MovieRental movieRental )
  {
    return "\t" + movieRental.movie().title() + "\t" + movieRental.computeAmount() + "\n";
  }

  private int getFrequentRenterPoints()
  {
    long count = rentals.stream()
      .filter( Customer::isEligibleForBonus )
      .count();

    return ( int ) count + rentals.size();
  }

  private static boolean isEligibleForBonus( MovieRental movieRental )
  {
    return ( movieRental.movie().movieType() == NEW_RELEASE ) && movieRental.daysRented() > 1;
  }
}
