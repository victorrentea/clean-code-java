package videostore.dirty;

import lombok.NonNull;

public class Movie {

	public enum Category {
		CHILDREN,
		REGULAR,
		NEW_RELEASE
	}
	private final String title;

	private final Category category;

	public Movie(@NonNull String title, Category category) {
		this.title = title;
		this.category = category;
	}

	public Category getCategory() {
		return category;
	}

	public String getTitle() {
		return title;
	};
}