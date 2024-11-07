package victor.training.cleancode.kata.videostore;
public class Movie {
	private final String _title;
	private final PriceCode _priceCode;

	public Movie(String title, PriceCode priceCode) {
		_title = title;
		_priceCode = priceCode;
	}

	public PriceCode getPriceCode() {
		return _priceCode;
	}

	public String getTitle() {
		return _title;
	}
}

enum PriceCode {
	REGULAR,
	NEW_RELEASE,
	CHILDRENS
}



