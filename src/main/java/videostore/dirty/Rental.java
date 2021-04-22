package videostore.dirty;

import org.junit.Test;
import videostore.dirty.Movie.Category;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;
import static java.util.Objects.requireNonNull;

public class Rental {
	private final Movie movie;
	private final int daysRented;
	private Map<Category, Supplier<Double>> fromPropertiesFile;

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

	public int computeFrequentRenterPoints() {
		int frequentRenterPoints = 1;
		boolean isNewRelease = getMovie().getPriceCode() == Category.NEW_RELEASE;
		if (isNewRelease && getDaysRented() >= 2) {
			frequentRenterPoints++;
		}
		return frequentRenterPoints;
	}
	public double computePrice() {
		// java17 : pt sefu
//		return switch (movie.getPriceCode()) {
//			case REGULAR -> computeRegularPrice();
//			case NEW_RELEASE -> computeNewReleasePrice();
//			case CHILDREN -> computeChildrenPrice();
//		};
//		Supplier<Double> f = priceFunctions.get(movie.getPriceCode());
//		if (f == null) {
//			throw new IllegalArgumentException();
//		}
//		return f.get();
//		switch (movie.getPriceCode()) {
//			case REGULAR: return computeRegularPrice();
//			case NEW_RELEASE: return computeNewReleasePrice();
//			case CHILDREN: return computeChildrenPrice();
//			default: throw new IllegalArgumentException();
//		}
		// @Autowired
		PriceCalculator priceCalculator = new PriceCalculator();
		return movie.getPriceCode().getPriceFunction().apply(priceCalculator,daysRented);
	}
	@Test
	public void test() {
		for (Category value : Category.values()) {
			// incearca sa chem functia cu switchul si vezi daca sare vreo exceptie de ille
		}
	}

//	Map<Category, Supplier<Double>> priceFunctions = fromPropertiesFile ofEntries(
//		entry(Category.REGULAR, this::computeRegularPrice),
//		entry(Category.NEW_RELEASE, this::computeNewReleasePrice),
//		entry(Category.CHILDREN, this::computeChildrenPrice)
//	);


}