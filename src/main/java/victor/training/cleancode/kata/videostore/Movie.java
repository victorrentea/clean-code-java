package victor.training.cleancode.kata.videostore;

import lombok.Data;

@Data
public class Movie {

	private final String title;
	Categories category;

	public Movie( String title, Categories category ) {
		this.title = title;
		this.category = category;
	}
}