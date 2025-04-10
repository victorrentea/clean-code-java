package victor.training.cleancode.kata.videostore;
public class Movie {
	public static final int CHILDREN = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	private final String title;
	private final Integer movieType;

	public Movie(String title, Integer movieType) {
		this.title = title;
		this.movieType = movieType;
	}

	public Integer getMovieType() {
		return movieType;
	}

	public String getTitle() {
		return title;
	};
}