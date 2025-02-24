package victor.training.cleancode.kata.videostore;

import java.util.ArrayList;
import java.util.List;

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

    rentals.forEach( mr -> result.append( mr.generateResult() ) );

    return result.append( "Amount owed is " )
			.append( rentals.stream().mapToDouble( MovieRental::computeAmount ).sum() )
      .append( "\n" )
			.append( "You earned " )
			.append( getFrequentRenterPoints() )
			.append( " frequent renter points" )
			.toString();
  }

  private int getFrequentRenterPoints()
  {
    return ( int ) ( rentals.stream()
          .filter( MovieRental::isEligibleForBonus )
          .count() + rentals.size());
  }

}
