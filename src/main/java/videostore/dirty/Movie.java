package videostore.dirty;

import java.util.function.BiFunction;

public class Movie {

	enum Category {
		CHILDREN(PriceCalculator::computeChildrenPrice),
		REGULAR(PriceCalculator::computeRegularPrice),
		NEW_RELEASE(PriceCalculator::computeNewReleasePrice);

		public final BiFunction<PriceCalculator, Integer, Double> priceAlgo;

		Category(BiFunction<PriceCalculator, Integer, Double> priceAlgo) {
			this.priceAlgo = priceAlgo;
		}
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