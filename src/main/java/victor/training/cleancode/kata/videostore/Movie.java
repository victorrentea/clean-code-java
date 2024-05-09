package victor.training.cleancode.kata.videostore;
//dummy comment

public class Movie {
    private String title;
    private MovieEnum priceCode;

    public Movie(String title, MovieEnum priceCode) {
        this.title = title;
        this.priceCode = priceCode;
    }

    public MovieEnum getPriceCode() {
        return priceCode;
    }

    public String getTitle() {
        return title;
    }

    ;
}