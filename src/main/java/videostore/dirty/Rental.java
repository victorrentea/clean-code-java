package videostore.dirty;

import videostore.dirty.Movie.Category;

import static java.util.Objects.requireNonNull;

public class Rental {
	private final Movie movie;
	private final int daysRented;

	public Rental(Movie movie, int daysRented) {
		this.movie = requireNonNull(movie);
		this.daysRented = daysRented;
	}

	public int getDaysRented() {
		return daysRented;
	}

	public Movie getMovie() {
		return movie;
	}


	 // este functie PURA
	public double computePrice() {
		double amount = 0; // random() == nu e Referential Transparent
		// INSERT METRICS, write file, change fields = "Side  Effects"
		switch (movie.getCategory()) {
		case REGULAR:
			amount += 2;
			if (daysRented > 2)
				amount += (daysRented - 2) * 1.5;
			break;
		case NEW_RELEASE:
			amount += daysRented * 3;
			break;
		case CHILDRENS:
			amount += 1.5;
			if (daysRented > 3)
				amount += (daysRented - 3) * 1.5;
			break;
		}
		return amount;
	}

	public int computeFrequentRenterPoints() {
		int frequentRenterPoints = 1;
		boolean isNewRelease = movie.getCategory() == Category.NEW_RELEASE;
		if (isNewRelease && daysRented >= 2) {
			frequentRenterPoints++;
		}
		return frequentRenterPoints;
	}
}