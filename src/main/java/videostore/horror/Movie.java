package videostore.horror;

enum MovieCategory {
	CHILDRENS,
	REGULAR,
	NEW_RELEASE
}
public class Movie {

	private final String title;
	private final MovieCategory priceCode;

	public Movie(String title, MovieCategory priceCode) {
		this.title = title;
		this.priceCode = priceCode;
	}

	public MovieCategory getPriceCode() {
		return priceCode;
	}

	public String getTitle() {
		return title;
	}
}