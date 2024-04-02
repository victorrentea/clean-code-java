package victor.training.cleancode.kata.videostore;

import lombok.Value;

@Value
public class Movie {
	String title;
	MovieTicketType priceCode;

	public Movie(String title, MovieTicketType priceCode) {
		this.title = title;
		this.priceCode = priceCode;
	}

}