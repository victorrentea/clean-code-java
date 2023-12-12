package victor.training.cleancode.kata.videostore.horror;


public class Movie {


	private final String title;
	private final PriceCode priceCode;

	public Movie(String title, PriceCode priceCode) {
		this.title = title;
		this.priceCode = priceCode;
	}

	public String getTitle() {
		return this.title;
	}

	public PriceCode getPriceCode() {
		return this.priceCode;
	}
}