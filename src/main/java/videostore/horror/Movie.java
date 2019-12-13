package videostore.horror;
public class Movie {


	enum Genre {
		REGULAR,
		NEW_RELEASE,
		CHILDREN;
	}
	private final String title;
	private final Genre genre;
	public Movie(String title, Genre genre) {
		this.title = title;
		this.genre = genre;
	}

	public boolean isNewRelease() {
		return getGenre() == Genre.NEW_RELEASE;
	}

	public Genre getGenre() {
		return genre;
	}

	public String getTitle() {
		return title;
	};
}