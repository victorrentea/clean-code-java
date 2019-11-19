package videostore.dirty;

public class Movie {
	public enum Category {
		CHILDREN,
		REGULAR,
		NEW_RELEASE
	}

	private final String title;
	private final Category category;

	public Movie(String title, Category category) {
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