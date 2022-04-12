package videostore.horror;

public class Movie {
	private final String title;
	private MovieCategory priceCode;

	public Movie(String title, MovieCategory priceCode) {
		this.title = title;
		this.priceCode = priceCode;
	}

	public MovieCategory getPriceCode() {
		return priceCode;
	}

	public void setPriceCode(MovieCategory priceCode) {
		this.priceCode = priceCode;
	}

	public String getTitle() {
		return title;
	};
}