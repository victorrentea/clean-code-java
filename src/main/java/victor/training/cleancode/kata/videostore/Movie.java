package victor.training.cleancode.kata.videostore;
public class Movie {

	enum MovieType {REGULAR, NEW_RELEASE, CHILDREN};
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	public static final int CHILDREN = 2;
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