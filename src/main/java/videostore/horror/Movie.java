package videostore.horror;

import static java.util.Objects.requireNonNull;

public class Movie {
	enum Category {
		CHILDREN,
		REGULAR,
		NEW_RELEASE
	}

	private final String title;
	private final Category priceCode;

	public Movie(String title, Category priceCode) {
		this.title = requireNonNull(title);
		this.priceCode = requireNonNull(priceCode);
	}

	public Category getPriceCode() {
		return priceCode;
	}

	public String getTitle() {
		return title;
	}
}