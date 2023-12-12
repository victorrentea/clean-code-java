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

    static int calculateRenterPoints(int frequentRenterPoints, Movie each, int noDaysRented) {

        // add frequent renter points
        frequentRenterPoints++;

        // add bonus for a two day new release rental
        if (each.getMovieCategory() != null &&
                (each.getMovieCategory() == MovieCategory.NEW_RELEASE)
                && noDaysRented > 1)
            frequentRenterPoints++;
        return frequentRenterPoints;
    }

     static double calculateAmountOfCurrentMovie(MovieCategory category,int noDaysRented){
        double thisAmount = 0;
        switch (category) {
            case REGULAR -> {
                thisAmount += 2;
                if (noDaysRented > 2)
                    thisAmount += (noDaysRented - 2) * 1.5;
            }
            case NEW_RELEASE -> thisAmount += noDaysRented * 3;
            case CHILDREN -> {
                thisAmount += 1.5;
                if (noDaysRented > 3)
                    thisAmount += (noDaysRented - 3) * 1.5;
            }
        }
        return thisAmount;
    }
}
