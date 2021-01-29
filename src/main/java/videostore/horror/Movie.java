package videostore.horror;
public class Movie {
	public static final int CHILDRENS = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	private String _title;
	private Integer _priceCode;

	public Movie(String title, Integer priceCode) {
		_title = title;
		_priceCode = priceCode;
	}

	public Integer getPriceCode() {
		return _priceCode;
	}

	public void setPriceCode(Integer arg) {
		_priceCode = arg;
	}

	public String getTitle() {
		return _title;
	};
}