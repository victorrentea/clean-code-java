package victor.training.cleancode.kata.videostore;

import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

import static victor.training.cleancode.kata.videostore.MovieType.NEW_RELEASE;

class Customer {
	@Getter
  private final String name;
	// preserves order of elements TODO find a better way to store this
	private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); 

	public Customer(String name) {
		this.name = name;
	}

  public void addRental(Movie movie, int d) {
		rentals.put(movie, d);
	}

  public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";

    for ( Movie movie : rentals.keySet() )
    {
      double thisAmount = 0;
      // determine amounts for every line
      int daysRented = rentals.get( movie );
      switch ( movie.movieType() )
      {
        case REGULAR:
          thisAmount += 2;
          if ( daysRented > 2 )
          {
            thisAmount += ( daysRented - 2 ) * 1.5;
          }
          break;
        case NEW_RELEASE:
          thisAmount += daysRented * 3;
          break;
        case CHILDREN:
          thisAmount += 1.5;
          if ( daysRented > 3 )
          {
            thisAmount += ( daysRented - 3 ) * 1.5;
          }
          break;
      }
      frequentRenterPoints++;
			
      if ( isEligibleForBonus( movie, daysRented ) )
      {
        frequentRenterPoints++;
      }
      // show figures line for this rental
      result += "\t" + movie.title() + "\t" + thisAmount + "\n";
      totalAmount += thisAmount;
    }
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private static boolean isEligibleForBonus( Movie movie, int dr )
	{
		return ( movie.movieType() == NEW_RELEASE ) && dr > 1;
	}
}
