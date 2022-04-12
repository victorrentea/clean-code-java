package videostore.horror;

import static java.util.Objects.requireNonNull;

public class Movie {
	private final String title;
	private final MovieCategory category;

	public Movie(String title, MovieCategory category) {
		this.title = requireNonNull(title);
		this.category = requireNonNull(category);
	}

	public MovieCategory getCategory() {
		return category;
	}

	public String getTitle() {
		return title;
	}
}