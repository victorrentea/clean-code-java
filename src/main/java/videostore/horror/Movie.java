package videostore.horror;

import static org.apache.commons.lang.StringUtils.isBlank;

import org.apache.commons.lang.StringUtils;

import videostore.horror.Movie.Type;

public class Movie {
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	public static final int CHILDRENS = 2;
	enum Type {
		REGULAR,
		NEW_RELEASE,
		CHILDRENS
	}
	
	private final String title;
	private final Type type;

	public Movie(final String title, final Type priceCode) {
		if (isBlank(title)) {
			throw new IllegalArgumentException();
		}
		this.title = title;
		this.type = priceCode;
	}

	public Type getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}
}