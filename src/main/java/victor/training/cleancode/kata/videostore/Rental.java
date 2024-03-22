package victor.training.cleancode.kata.videostore;

import static victor.training.cleancode.kata.videostore.MovieCategory.NEW_RELEASE;

public record Rental(Movie movie, int rentalDays) {

    boolean isEligibleForBonusPoints() {
        return movie.category() == NEW_RELEASE && rentalDays > 1;
    }

    double calculatePrice() {
        // determine amounts for every line
        return switch (movie.category()) {
            case REGULAR -> regularPrice();
            case NEW_RELEASE -> rentalDays * 3;
            case CHILDRENS -> childrensPrice();
        };
    }

    private double childrensPrice() {
        double price = 1.5;
        if (rentalDays > 3)
            price += (rentalDays - 3) * 1.5;
        return price;
    }

    private double regularPrice() {
        double price = 2;
        if (rentalDays > 2)
            price += (rentalDays - 2) * 1.5;
        return price;
    }
}
