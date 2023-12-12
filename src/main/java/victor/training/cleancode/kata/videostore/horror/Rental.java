package victor.training.cleancode.kata.videostore.horror;

public class Rental {
    private final Movie movie;
    private final int rentalNumber;


    public int getRentalPoints() {
        int rentalPoints=0;
        if(movie.getPriceCode().equals(PriceCode.NEW_RELEASE) && rentalNumber > 1) {
             rentalPoints++;
        }
        rentalPoints++;
        return rentalPoints;
    }

    public double calculatePrice() {
        double thisAmount = 0;

        switch (this.movie.getPriceCode()) {
            case REGULAR -> {
                thisAmount += 2;
                if (rentalNumber > 2)
                    thisAmount += (rentalNumber - 2) * 1.5;
            }
            case NEW_RELEASE -> thisAmount += rentalNumber * 3;
            case CHILDREN -> {
                thisAmount += 1.5;
                if (rentalNumber > 3)
                    thisAmount += (rentalNumber - 3) * 1.5;
            }

            default -> throw new IllegalStateException("Unexpected value: " + this.movie.getPriceCode());
        }
        return thisAmount;
    }

    public Rental(Movie movie, int rentalNumber) {
        this.movie = movie;
        this.rentalNumber = rentalNumber;
    }

    public Movie getMovie() {
        return movie;
    }
}
