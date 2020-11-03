package videostore.horror;

import java.util.function.Function;

import static java.util.Objects.requireNonNull;

public class Movie {
	enum Type {
		CHILDREN(PriceCalculator::computeChildrenPrice),
		REGULAR(PriceCalculator::computeRegularPrice),
		NEW_RELEASE(PriceCalculator::computeNewReleasePrice);
		private final Function<Integer, Double> priceCalculatorFunc;

		Type(Function<Integer, Double> priceCalculatorFunc) {
			this.priceCalculatorFunc = priceCalculatorFunc;
		}

		public double computePrice(int daysRented) {
			return priceCalculatorFunc.apply(daysRented);
		}
	}
	private final String title;
	private final Type type;

	public Movie(String title, Type type) {
		this.title = title;
		this.type = requireNonNull(type);
		// requires a db migration: UPDATE MOVIES SET TYPE='CHILDREN' WHERE TYPE IS NULL;
		// + ALTER TABLE MOVIES SET TYPE NOT NULL;

		// daca-s ff multe --> PROCEDURA temporara care migreaza datele. dupa care o stergi.
	}

	public Type getType() {
		return type;
	}

	public String getTitle() {
		return title;
	};
}