package victor.training.cleancode.kata.videostore;

import victor.training.cleancode.kata.videostore.Movie.PriceCategory;

import java.util.ArrayList;
import java.util.List;

class Customer
{
  private final String name;
  private final List<Rental> rentals = new ArrayList<>();
  private int frequentRenterPoints = 0;
  // preserves order of elements TODO find a better way to store this

  public Customer( String name )
  {
    this.name = name;
  }

  public void addRental( Movie movie, int days )
  {
    rentals.add( new Rental( movie, days ) );
  }

  public String rentMovies()
  {
    double totalAmount = getTotalAmount();

    addFrequentRenterPoints();
    return generateReceipt( totalAmount );
  }

  private double getTotalAmount()
  {
    // loop over each movie rental
    double totalAmount = 0;
    for ( Rental rental : rentals )
    {
      totalAmount += rental.getCost();
    }
    return totalAmount;
  }

  private String generateReceipt( double totalAmount )
  {
    StringBuilder result = new StringBuilder( "Rental Record for " + name + "\n" );
    for ( Rental rental : rentals )
    {
      result.append( rental );
    }

    // add footer lines
    result.append( "Amount owed is " ).append( totalAmount ).append( "\n" );
    result.append( "You earned " ).append( frequentRenterPoints ).append( " frequent renter points" );
    return result.toString();
  }

  private void addFrequentRenterPoints()
  {
    for ( Rental rental : rentals )
    {
      // add frequent renter points
      frequentRenterPoints++;
      // add bonus for a two-day new release rental
      if ( rental.movie().priceCategory() == PriceCategory.NEW_RELEASE && rental.days() > 1 )
      {
        frequentRenterPoints++;
      }
    }
  }
}