package videostore.horror;
public class Movie {

	private final String title;
	private final MovieType priceCode;

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

	;
}