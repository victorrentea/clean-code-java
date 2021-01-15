package victor.training.java8;


import java.util.function.BiFunction;

class PriceService {
//	private final FactorRepo repo;

	public int computePrice(Movie.Type type, int days) {
		return type.getPriceAlgorithm().apply(this, days);
	}

	public int computeChildrenPrice(int days) {
		return 5;
	}

	public int computeNewReleasePrice(int days) {
		return days * 2;
	}

	public int computeRegularPrice(int days) {
		return days + 1;
	}
}

class Movie {
	enum Type {
		REGULAR(PriceService::computeRegularPrice),
		NEW_RELEASE(PriceService::computeNewReleasePrice),
		CHILDREN(PriceService::computeChildrenPrice);
		private final BiFunction<PriceService, Integer, Integer> priceAlgorithm;

		Type(BiFunction<PriceService, Integer, Integer> priceAlgorithm) {
			this.priceAlgorithm = priceAlgorithm;
		}

		public BiFunction<PriceService, Integer, Integer> getPriceAlgorithm() {
			return priceAlgorithm;
		}
	}

	private final Type type;

	public Movie(Type type) {
		this.type = type;
	}




}

public class E__TypeSpecific_Functionality {
	public static void main(String[] args) {
		System.out.println(new PriceService().computePrice(Movie.Type.REGULAR, 2));
		System.out.println(new PriceService().computePrice(Movie.Type.NEW_RELEASE, 2));
		System.out.println(new PriceService().computePrice(Movie.Type.CHILDREN, 2));
	}
}
