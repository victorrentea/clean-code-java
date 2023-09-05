package videostore.horror;


public class Movie {

	private final String title;
	private final Integer priceCode;

	public Movie(String title, Integer priceCode) {
		this.title = title;
		this.priceCode = priceCode;
	}

	public Integer getPriceCode() {
		return priceCode;
	}

	public String getTitle() {
		return title;
	};
}