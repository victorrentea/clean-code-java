package videostore.horror;
public class Movie {
	public static final int CHILDRENS = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;

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
	};
}