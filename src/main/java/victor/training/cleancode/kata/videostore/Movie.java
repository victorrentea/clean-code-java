package victor.training.cleancode.kata.videostore;
public class Movie {
	public static final int CHILDRENS = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	private final String title;
	private Integer priceCode;

	public Movie(String title, Integer priceCode) {
		this.title = title;
		this.priceCode = priceCode;
	}

	public Integer getPriceCode() {
		return priceCode;
	}

	public void setPriceCode(Integer arg) {
		priceCode = arg;
	}

	public String getTitle() {
		return title;
	};
}