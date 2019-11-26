package videostore.dirty;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

class Rental {
    private final Movie movie;
    private final int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public int computeBonusRenterPoints() {
        int frequentRenterPoints = 1;
        boolean isNewRelease = movie.getMovieType() == MovieType.NEW_RELEASE;
        if (isNewRelease && daysRented >= 2) {
            frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }

    public double computePrice() {
        return movie.getMovieType().priceCalculator.apply(daysRented);
    }


	public static double computeRegularMoviePrice(int daysRented) {
		double price = 2;
		if (daysRented > 2)
			price += (daysRented - 2) * 1.5;
		return price;
	}

	public static double computeNewReleaseMoviePrice(int daysRented) {
		return daysRented * 3;
	}

	public static double computeChildrenMoviePrice(int daysRented) {
        double price = 0;
        price += 1.5;
        if (daysRented > 3)
            price += (daysRented - 3) * 1.5;
        return price;
    }
}