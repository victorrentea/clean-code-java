package videostore.horror;

public class Movie {
	private final String title;

	private final int priceCode;

	public Movie(String title, int priceCode) {
		this.title = title;
		this.priceCode = priceCode;
	}

	public int getPriceCode() {
		return priceCode;
	}


	public String getTitle() {
		return title;
	}
}