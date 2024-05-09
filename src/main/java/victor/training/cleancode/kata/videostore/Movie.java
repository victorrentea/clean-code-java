package victor.training.cleancode.kata.videostore;
public class Movie {
	private String title;
	private Integer priceCode;

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