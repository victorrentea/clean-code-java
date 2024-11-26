package victor.training.cleancode.kata.videostore;
import lombok.Getter;

@Getter
public class Movie {
	private final String title;
	private final MovieType movieType;

	public Movie(String title, MovieType movieType) {
		this.title = title;
		this.movieType = movieType;
	}

}