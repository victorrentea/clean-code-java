package videostore.dirty;

import static org.apache.commons.lang.StringUtils.isBlank;

import org.apache.commons.lang.StringUtils;
import org.hamcrest.core.IsNot;

public class Movie {
	
	enum Category {
		CATEGORY_CHILDRENS,
		CATEGORY_REGULAR,
		CATEGORY_NEW_RELEASE
	}
	
//	public static final int CATEGORY_CHILDRENS = 2;
//	public static final int CATEGORY_REGULAR = 0;
//	public static final int CATEGORY_NEW_RELEASE = 1;
	private final String title;
	private final Category priceCode;

	public Movie(String title, Category priceCode) {
		if (isBlank(title)) {
			throw new IllegalArgumentException();
		}
		this.title = title;
		this.priceCode = priceCode;
	}

	public Category getPriceCode() {
		return priceCode;
	}

	public String getTitle() {
		return title;
	}
}