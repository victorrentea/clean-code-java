package videostore.horror;

public class RentalMovie {

    private final Movie movie;

    private final int rentalDays;

    public RentalMovie(Movie movie, int rentalDays) {
        this.movie = movie;
        this.rentalDays = rentalDays;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    double calculateMoviePrice() {
        double price = 0;
        switch (movie.getPriceFactor()) {
        case REGULAR:
            price += 2;
            if (rentalDays > 2)
                price += (rentalDays - 2) * 1.5;
            break;
        case NEW_RELEASE:
            price += rentalDays * 3;
            break;
        case CHILDREN:
            price += 1.5;
            if (rentalDays > 3)
                price += (rentalDays - 3) * 1.5;
            break;

        default:
            throw new IllegalStateException(
                "Unexpected value: " + movie.getPriceFactor());
        }
    return price;
}

    int calculateRenterPoints() {
    int result =1;
    if (movie.getPriceFactor() == PriceFactor.NEW_RELEASE && rentalDays >= 2)
        result++;
    return result;
}
}
