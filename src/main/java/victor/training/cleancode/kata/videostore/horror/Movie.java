package victor.training.cleancode.kata.videostore.horror;

import java.util.Objects;

public class Movie {

	private final String title;
	private Integer priceCode;

	public Movie(String title, Integer priceCode) {
		this.title = Objects.requireNonNull(title);
		this.priceCode = Objects.requireNonNull(priceCode);
	}

	public Integer getPriceCode() {
		return priceCode;
	}

	public void setPriceCode(Integer arg) {
		priceCode = arg;
	}

	public String getTitle() {
		return title;
	};
}