package victor.training.cleancode.kata.videostore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Customer {
    private final String name;
    private final List<RentedMovie> rentedMovies = new ArrayList<>();

    public Customer(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public void addRental(Movie movie, int daysOfRental) {
        rentedMovies.add(new RentedMovie(movie, daysOfRental));
    }

    public String getName() {
        return name;
    }

    public String statement() {
        List<Statement.MoviePrice> moviePrices = rentedMovies.stream()
                .map(m -> new Statement.MoviePrice(m.movie().title(), m.computePrice()))
                .toList();
        Statement statement = new Statement(name, moviePrices, computeFrequentRenterPoints());
        return statement.format();
    }

    private long computeFrequentRenterPoints() {
		long frequentRenterBonusPoints =  rentedMovies.stream()
				.filter(RentedMovie::isEligibleForFrequentRenterBonus).count();
		long frequentRenterPoints = rentedMovies.size();
        return frequentRenterBonusPoints + frequentRenterPoints;
    }
}