package videostore.horror;

import java.util.Objects;

public class Movie {
	enum Category {
		CHILDREN,
		NEW_RELEASE,
		REGULAR
	}

	private final String title;
	private final Category category;

	public Movie(String title, Category category) {
		this.title = Objects.requireNonNull(title);
		this.category = category;
	}

	public Category getCategory() {
		return category;
	}

	public String getTitle() {
		return title;
	};
}