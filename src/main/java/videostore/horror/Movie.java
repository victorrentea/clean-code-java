package videostore.horror;
public class Movie implements IMovie {
	public static final int CHILDRENS = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	private String _title;
	private Integer _priceCode;

	public Movie(String title, Integer priceCode) {
		_title = title;
		_priceCode = priceCode;
	}

	@Override
	public Integer getPriceCode() {
		return _priceCode;
	}

	@Override
	public void setPriceCode(Integer arg) {
		_priceCode = arg;
	}

	@Override
	public String getTitle() {
		return _title;
	};
}