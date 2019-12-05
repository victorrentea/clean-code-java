package videostore.dirty;

import java.util.function.BiFunction;

public class Movie {

	enum Type {
		REGULAR(PriceCalculationService::computeRegularPrice),
		NEW_RELEASE(PriceCalculationService::computeNewReleasePrice),
		CHILDREN(PriceCalculationService::computeChildrenPrice);
		public final BiFunction<PriceCalculationService,Integer,Double> priceAlgo;

        Type(BiFunction<PriceCalculationService, Integer, Double> priceAlgo) {
            this.priceAlgo = priceAlgo;
        }

//        public double computePrice(PriceCalculationService service, Integer daysRented) {
//            return priceAlgo.apply(service, daysRented);
//        }
    }

	private final String title;

    private final Type type;

    public Movie(String title, Type type) {
        this.title = title;
        this.type = type;
    }


    public Type getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

}