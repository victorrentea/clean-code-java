package videostore.dirty;

import static org.apache.commons.lang.StringUtils.isBlank;

import org.apache.commons.lang.StringUtils;
import org.hamcrest.core.IsNot;

public class Movie {
	
	
	public static final int CATEGORY_CHILDRENS = 2;
	public static final int CATEGORY_REGULAR = 0;
	public static final int CATEGORY_NEW_RELEASE = 1;
	private final String title;
	private final int priceCode;

	public Movie(String title, int priceCode) {
		if (isBlank(title)) {
			throw new IllegalArgumentException();
		}
		this.title = title;
		this.priceCode = priceCode;
	}

	public int getPriceCode() {
		return priceCode;
	}

	public String getTitle() {
		return title;
	}
}