package victor.training.cleancode.kata.videostore;
public class Movie {

	private final MovieType priceCode;

	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	public static final int CHILDREN = 2;
	private final String title;
	public Movie(String title, MovieType priceCode) {
		this.title = title;
		this.priceCode = priceCode;
	}

	public MovieType getPriceCode() {
		return priceCode;
	}

	public enum MovieType {
		REGULAR(0),
		NEW_RELEASE(1),
		CHILDREN(2);

		private final int value;

		MovieType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	public String getTitle() {
		return title;
	}
}