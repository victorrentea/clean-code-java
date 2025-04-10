package victor.training.cleancode.kata.videostore;

import static victor.training.cleancode.kata.videostore.MovieType.NEW_RELEASE;

record Rental(Movie movie, int daysRented) {

    public Double getRentalPrice() {
        double rentalPrice = 0;

        switch (this.movie().movieType()) {
            case REGULAR:
                rentalPrice += 2;
                if (this.daysRented() > 2) rentalPrice += (this.daysRented() - 2) * 1.5;
                break;
            case NEW_RELEASE:
                rentalPrice += this.daysRented() * 3;
                break;
            case CHILDREN:
                rentalPrice += 1.5;
                if (this.daysRented() > 3) rentalPrice += (this.daysRented() - 3) * 1.5;
                break;
        }

        return rentalPrice;
    }

    public int computeFrequentRenterPoints() {
        if (this.movie().movieType() != null && (this.movie().movieType() == NEW_RELEASE) && this.daysRented() > 1) {
            return 2;
        }
        return 1;
    }
}
