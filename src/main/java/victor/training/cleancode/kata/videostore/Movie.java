package victor.training.cleancode.kata.videostore;
public class Movie {

	private final MovieType priceCode;

	private final String title;
	public Movie(String title, MovieType priceCode) {
		this.title = title;
		this.priceCode = priceCode;
	}

	public MovieType getPriceCode() {
		return priceCode;
	}

	public String getTitle() {
		return title;
	}
}