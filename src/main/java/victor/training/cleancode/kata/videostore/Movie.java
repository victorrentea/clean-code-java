package victor.training.cleancode.kata.videostore;
public class Movie {
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	public static final int CHILDRENS = 2;
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
	};
}