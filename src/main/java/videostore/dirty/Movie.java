package videostore.dirty;

import cleancode.pretend.Autowired;
import cleancode.pretend.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

@Service
class PriceCalculatorService {
//	@Autowired
//	private stuff

	@FunctionalInterface
	interface PriceAlgorithm {
		double compute(int daysRented);
	}

	public double computeChildrenPrice(int daysRented) {
		double price = 1.5;
		if (daysRented > 3)
			price += (daysRented - 3) * 1.5;
		return price;
	}

	public double computeNewReleasePrice(int daysRented) {
		return daysRented * 3;
	}

	public double computePrice(Movie.Category category, int daysRented) {
		return Movie.algorithmMap.get(category).apply(this, daysRented);
	}

	public double computeRegularPrice(int daysRented) {
		double price = 2;
		if (daysRented > 2)
			price += (daysRented - 2) * 1.5;
		return price;
	}
}

public class Movie {
	public enum Category {
		CHILDREN,
		REGULAR,
		NEW_RELEASE;
	}
	public static final Map<Category, BiFunction<PriceCalculatorService, Integer, Double>> algorithmMap = new HashMap<>();
	static {
		algorithmMap.put(Category.REGULAR, PriceCalculatorService::computeRegularPrice);
		algorithmMap.put(Category.CHILDREN, PriceCalculatorService::computeChildrenPrice);
		algorithmMap.put(Category.NEW_RELEASE, PriceCalculatorService::computeNewReleasePrice);
	}

	private final String title;
	private final Category category;

	public Movie(String title, Category category) {
		this.title = title;
		this.category = category;
	}

	public Category getCategory() {
		return category;
	}

	public String getTitle() {
		return title;
	};
}