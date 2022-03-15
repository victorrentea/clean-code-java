package videostore.horror;

//import videostore.horror.Movie.Message.PriceCode;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Movie {
	public boolean isNewRelease() {
		return priceCode() == PriceCode.NEW_RELEASE;
	}

//	enum MessageType {
//		TYPE1(MessageHandlers::handleMessageType1),
//	}
	enum PriceCode {
		REGULAR(PriceService::computeRegularPrice),
		CHILDREN(PriceService::computeChildrenPrice),
//	ELDERS(null),
	NEW_RELEASE(PriceService::computeNewReleasePrice);


		public final BiFunction<PriceService, Integer, Double> priceComputation;

		PriceCode(BiFunction<PriceService, Integer, Double> priceComputation) {
			this.priceComputation = priceComputation;
		}
	}
	private final String title;
	private final PriceCode priceCode;

	public Movie(String title, PriceCode priceCode) {
		this.title = Objects.requireNonNull(title); // + NOT NULL in DB
		this.priceCode = Objects.requireNonNull(priceCode);
	}

	public PriceCode priceCode() {
		return priceCode;
	}

	public String title() {
		return title;
	};
}