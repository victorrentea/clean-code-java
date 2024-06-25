package victor.training.cleancode.kata.videostore;

import lombok.Data;

@Data
public class Movie {
	private String title;
	private MovieType movieType;
}