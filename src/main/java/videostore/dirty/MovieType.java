package videostore.dirty;

import java.util.function.BiFunction;
import java.util.function.Function;

public enum MovieType {
	CHILDREN(PriceCalculators::computeChildrenMoviePrice),
	REGULAR(PriceCalculators::computeRegularMoviePrice),
//	ELDERS(null),
	NEW_RELEASE(PriceCalculators::computeNewReleaseMoviePrice);

	public final BiFunction<PriceCalculators, Integer, Double> priceCalculator;

	MovieType(BiFunction<PriceCalculators, Integer, Double>  priceCalculator) {
		this.priceCalculator = priceCalculator;
	}
}
