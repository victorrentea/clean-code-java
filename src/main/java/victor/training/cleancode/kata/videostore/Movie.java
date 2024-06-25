package victor.training.cleancode.kata.videostore;

import lombok.Getter;

public class Movie {

    @Getter
    private String _title;
    @Getter
    private MovieType _priceCode;

    public Movie(String title, MovieType priceCode) {
        _title = title;
        _priceCode = priceCode;
    }
}