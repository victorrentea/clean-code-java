package videostore.horror;

public final class Movie {


	private final String title;
	private final MovieCategory category;



	public Movie(String title, MovieCategory category) {
		this.title = title;
		this.category = category;
	}

	public String getTitle() {
		return this.title;
	}

	public MovieCategory getCategory() {
		return this.category;
	}
}
