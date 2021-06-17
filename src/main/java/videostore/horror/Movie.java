package videostore.horror;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Movie {
	public static final int CHILDREN = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	private final String title;
	private final Integer priceCode;

	public Movie(String title, Integer priceCode) {
		this.title = requireNonNull(title);
		this.priceCode = requireNonNull(priceCode);
	}

	public Integer getPriceCode() {
		return priceCode;
	}

	public String getTitle() {
		return title;
	}
}