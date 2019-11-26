package videostore.dirty;

import java.util.function.Function;

public enum MovieType {
	CHILDREN(Rental::computeChildrenMoviePrice),
	REGULAR(Rental::computeRegularMoviePrice),
//	ELDERS(null),
	NEW_RELEASE(Rental::computeNewReleaseMoviePrice);

	public final Function<Integer, Double> priceCalculator;

	MovieType(Function<Integer, Double> priceCalculator) {
		this.priceCalculator = priceCalculator;
	}
}
