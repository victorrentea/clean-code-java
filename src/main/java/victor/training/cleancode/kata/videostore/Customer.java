package victor.training.cleancode.kata.videostore;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static victor.training.cleancode.kata.videostore.MovieType.NEW_RELEASE;

class Customer {
	@Getter
  private final String name;
	private final List<MovieRental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	}

  public void addRental(Movie movie, int daysRented) {
		rentals.add(new MovieRental( movie, daysRented ));
	}

  public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";

    for ( MovieRental movieRental : rentals )
    {
			double thisAmount = computeAmount( movieRental );
			frequentRenterPoints++;
			
      if ( isEligibleForBonus( movieRental ) )
      {
        frequentRenterPoints++;
      }
      // show figures line for this rental
      result += "\t" + movieRental.movie().title() + "\t" + thisAmount + "\n";
      totalAmount += thisAmount;
    }
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
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
				break;
			case NEW_RELEASE:
				amount += daysRented * 3;
				break;
			case CHILDREN:
				amount += 1.5;
				if ( daysRented > 3 )
				{
					amount += ( daysRented - 3 ) * 1.5;
				}
				break;
		}
		return amount;
	}

	private static boolean isEligibleForBonus( MovieRental movieRental )
	{
		return ( movieRental.movie().movieType() == NEW_RELEASE ) && movieRental.daysRented() > 1;
	}
}
