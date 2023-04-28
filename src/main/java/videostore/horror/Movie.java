package videostore.horror;

import java.util.Objects;

import static java.util.Objects.requireNonNull;


public class Movie {
	private final String title;
	private final PriceFactor priceFactor;

	public Movie(String title, PriceFactor priceFactor) {
		this.title = requireNonNull(title);
		this.priceFactor = requireNonNull(priceFactor);
	}


	public String getTitle() {
		return title;
	}

	public PriceFactor getPriceFactor() {
		return priceFactor;
	}
}
