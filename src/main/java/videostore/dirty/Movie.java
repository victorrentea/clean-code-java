package videostore.dirty;

import java.util.function.BiFunction;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

public class Movie {
	public enum Category {
		CHILDREN(PriceCalculator::computeChildrenPrice),
		REGULAR(PriceCalculator::computeRegularPrice),
		NEW_RELEASE(PriceCalculator::computeNewReleasePrice);
		//		PT_BABACI
		private final BiFunction<PriceCalculator, Integer, Double> priceFunction;

		Category(BiFunction<PriceCalculator, Integer, Double> priceFunction) {
			this.priceFunction = priceFunction;
		}

		public BiFunction<PriceCalculator, Integer, Double> getPriceFunction() {
			return priceFunction;
		}
	}
	private final String title;
	private final Category priceCode;

	public Movie(String title, Category priceCode) {
		this.title = title;
		this.priceCode = requireNonNull(priceCode);
	}

	public Category getPriceCode() {
		return priceCode;
	}

	public String getTitle() {
		return title;
	}
}