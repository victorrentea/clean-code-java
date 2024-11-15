package victor.training.cleancode.kata.videostore;

public class Movie {
	private final String mTitle;
	private final PriceCategory mPriceCategory;

	public Movie(String pTitle, PriceCategory pPriceCategory) {
		mTitle = pTitle;
		mPriceCategory = pPriceCategory;
	}

	public PriceCategory getPriceCode() {
		return mPriceCategory;
	}

	public String getTitle() {
		return mTitle;
	}
}