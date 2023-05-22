package videostore.horror;
public class Movie {

	enum PriceCode {
		CHILDREN (2),
		REGULAR (0),
		NEW_RELEASE (1);

		private final int index;

		PriceCode(int i) {
			index = i;
		}
	}

	public static final int CHILDREN = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	private final String title;
	private final int priceCode;

	public Movie(String title, PriceCode priceCode) {
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