package victor.training.cleancode.kata.videostore;
public class Movie {

	private String _title;
	MovieType _priceCode;

	public Movie(String title, MovieType priceCode) {
		_title = title;
		_priceCode = priceCode;
	}

	public MovieType getPriceCode() {
		return _priceCode;
	}

	public void setPriceCode(MovieType arg) {
		_priceCode = arg;
	}

	public String getTitle() {
		return _title;
	};
}