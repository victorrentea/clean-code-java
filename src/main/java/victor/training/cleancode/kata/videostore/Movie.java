package victor.training.cleancode.kata.videostore;
public class Movie {

	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	public static final int CHILDREN = 2;
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
	}
}