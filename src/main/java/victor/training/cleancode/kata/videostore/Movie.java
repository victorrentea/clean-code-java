package victor.training.cleancode.kata.videostore;

import lombok.Data;

@Data
public class Movie {

	private final String title;
	Categories priceCode;

	public Movie( String title, Categories priceCode) {
		this.title = title;
		this.priceCode = priceCode;
	}

	public Categories getPriceCode() {
		return priceCode;
	}

	public String getTitle() {
		return title;
	};
}