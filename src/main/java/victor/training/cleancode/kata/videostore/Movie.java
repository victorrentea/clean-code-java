package victor.training.cleancode.kata.videostore;

import lombok.Getter;

@Getter
public class Movie {

	/**
	public static final int CHILDREN = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	 **/

	private final String title;
	//private final int priceCode;
	private final MovieType movieType;

	public Movie(String title, MovieType movieType) {
		this.title = title;
		this.movieType = movieType;
	}
}