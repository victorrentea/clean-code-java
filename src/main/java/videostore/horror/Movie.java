package videostore.horror;

enum MovieCategory {
	CHILDRENS,
	REGULAR,
	NEW_RELEASE
}
public class Movie {

	private final String title;
	private final MovieCategory movieCategory;

	public Movie(String title, MovieCategory movieCategory) {
		this.title = title;
		this.movieCategory = movieCategory;
	}

	public MovieCategory getMovieCategory() {
		return movieCategory;
	}

	public String getTitle() {
		return title;
	}
}