package victor.training.cleancode.kata.videostore;

import lombok.Data;

@Data
public class Movie {

	private final String title;
	Category category;

	public Movie( String title, Category category ) {
		this.title = title;
		this.category = category;
	}
}
