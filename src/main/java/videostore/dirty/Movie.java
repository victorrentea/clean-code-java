package videostore.dirty;

import static org.apache.commons.lang.StringUtils.isBlank;

public class Movie {
	
	public enum Category {
		CHILDRENS,
		REGULAR,
		NEW_RELEASE
	}

	private final String title;
	private final Category category;

	public Movie(String title, Category category) {
		if (isBlank(title)) {
			throw new IllegalArgumentException();
		}
		this.title = title;
		this.category = category;
	}

	public Category getCategory() {
		return category;
	}

	public String getTitle() {
		return title;
	}

	public boolean isNewRelease() {
		return getCategory() == Movie.Category.NEW_RELEASE;
	}
}