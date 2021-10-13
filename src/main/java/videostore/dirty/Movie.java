package videostore.dirty;

import java.util.function.Function;

public class Movie {
	public static final int CATEGORY_CHILDRENS = 2;
	public static final int CATEGORY_REGULAR = 0;
	public static final int CATEGORY_NEW_RELEASE = 1;

	enum PriceCode {
		CATEGORY_CHILDRENS {
			@Override
			public double computePrice(Rental rental) {
				return 0;
			}
		},
		CATEGORY_REGULAR {},
		CATEGORY_NEW_RELEASE {};

		public abstract double computePrice(Rental rental);

	}
	private String _title;
	private PriceCode _priceCode;

	public Movie(String title, PriceCode priceCode) {
		_title = title;
		_priceCode = priceCode;
	}

	public PriceCode getPriceCode() {
		return _priceCode;
	}

	public String getTitle() {
		return _title;
	};
}