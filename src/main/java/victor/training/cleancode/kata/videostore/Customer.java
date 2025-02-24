package victor.training.cleancode.kata.videostore;

import lombok.Getter;
import victor.training.cleancode.kata.videostore.Movie.Genre;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    String result = "Rental Record for " + getName() + "\n";
    // loop over each movie rental
    for ( Rental rental : rentals )
    {
      // determine amounts for every line
      int daysRented = rental.days();
      double cost = getCost( rental, daysRented );
      // add frequent renter points
      frequentRenterPoints++;
      // add bonus for a two-day new release rental
      if ( rental.priceCode() == Genre.NEW_RELEASE && daysRented > 1 )
      {
        frequentRenterPoints++;
      }
      // show figures line for this rental
      result += "\t" + rental.title() + "\t" + cost + "\n";
      totalAmount += cost;
    }
    // add footer lines
    result += "Amount owed is " + totalAmount + "\n";
    result += "You earned " + frequentRenterPoints + " frequent renter points";
    return result;
  }

  private static double getCost( Movie movie, int daysRented )
  {
    double cost = 0;
    switch ( movie.priceCode() )
    {
      case REGULAR ->
      {
        cost += 2;
        if ( daysRented > 2 )
        {
          cost += ( daysRented - 2 ) * 1.5;
        }
      }
      case NEW_RELEASE -> cost += daysRented * 3;
      case CHILDREN ->
      {
        cost += 1.5;
        if ( daysRented > 3 )
        {
          cost += ( daysRented - 3 ) * 1.5;
        }
      }
    }
    return cost;
  }
}