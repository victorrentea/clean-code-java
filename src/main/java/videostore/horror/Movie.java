package videostore.horror;

import java.util.Objects;

public class Movie {
	enum PriceCode {
		REGULAR,
		CHILDREN,
		NEW_RELEASE
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