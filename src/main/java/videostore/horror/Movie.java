package videostore.horror;

import static org.apache.commons.lang.StringUtils.isBlank;

import java.util.function.Function;

public class Movie {
	enum Type { // nested as long as this is the only class with a field of this type.
		REGULAR(RentalPriceCalculator::calculateRegularPrice),
		NEW_RELEASE(RentalPriceCalculator::calculateNewRelease),
		CHILDRENS(RentalPriceCalculator::calculateChildernPrice)
		;
		public final Function<Integer, Double> priceAlgo;

		private Type(Function<Integer, Double> priceAlgo) {
			this.priceAlgo = priceAlgo;
		}
		
	}
	
	private final String title;
	private final Type type;

	public Movie(final String title, final Type priceCode) {
		if (isBlank(title)) {
			throw new IllegalArgumentException();
		}
		this.title = title;
		this.type = priceCode;
	}

	public Type getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

	public boolean isNewRelease() {
		return type == Type.NEW_RELEASE;
	}
}