package victor.training.cleancode.kata.videostore.horror;

import java.util.Objects;

public class Movie {

	private final String title;
	private final MovieCategory movieCategory;

	public Movie(String title, MovieCategory movieCategory) {
		this.title = Objects.requireNonNull(title);
		this.movieCategory = Objects.requireNonNull(movieCategory);
	}

	public MovieCategory getMovieCategory() {
		return movieCategory;
	}

	public String getTitle() {
		return title;
	};
}