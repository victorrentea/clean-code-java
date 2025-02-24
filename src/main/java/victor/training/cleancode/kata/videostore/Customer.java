package victor.training.cleancode.kata.videostore;

import lombok.Getter;
import victor.training.cleancode.kata.videostore.Movie.PriceCategory;

import java.util.ArrayList;
import java.util.List;

class Customer
{
  @Getter
  private final String name;
  private final List<Rental> rentals = new ArrayList<>();
    // preserves order of elements TODO find a better way to store this

  public Customer( String name )
  {
    this.name = name;
  }

  public void addRental( Movie movie, int days )
  {
    rentals.add( new Rental( movie, days ) );
  }

  public String statement()
  {
    double totalAmount = 0;
    int frequentRenterPoints = 0;
    StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");
    // loop over each movie rental
    for ( Rental rental : rentals )
    {
      // determine amounts for every line
      int daysRented = rental.days();
      double cost = rental.getCost( );
      // add frequent renter points
      frequentRenterPoints++;
      // add bonus for a two-day new release rental
      if ( rental.movie().priceCategory() == PriceCategory.NEW_RELEASE && daysRented > 1 )
      {
        frequentRenterPoints++;
      }
      // show figures line for this rental
      result.append(rental);
      totalAmount += cost;
    }
    // add footer lines
    result.append("Amount owed is ").append(totalAmount).append("\n");
    result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
    return result.toString();
  }


}