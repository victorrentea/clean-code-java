package victor.training.cleancode.kata.videostore.horror;

import java.util.Objects;

public class Rental {

   private Movie movie;

   private Integer noDaysRented;

    public Rental(Movie movie, Integer noDaysRented) {
        this.movie = Objects.requireNonNull(movie);
        this.noDaysRented = Objects.requireNonNull(noDaysRented);
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Integer getNoDaysRented() {
        return noDaysRented;
    }

    public void setNoDaysRented(Integer noDaysRented) {
        this.noDaysRented = noDaysRented;
    }
}
