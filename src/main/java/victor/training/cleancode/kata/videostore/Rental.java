package victor.training.cleancode.kata.videostore;

public record Rental(Movie movie, int rentalDays) {
    double calculateCosts(int rentalDays) {
        double thisAmount = 0;
        // determine amounts for every line
        switch (movie.category()) {
            case REGULAR:
                thisAmount += 2;
                if (rentalDays > 2)
                    thisAmount += (rentalDays - 2) * 1.5;
                break;
            case NEW_RELEASE:
                thisAmount += rentalDays * 3;
                break;
            case CHILDRENS:
                thisAmount += 1.5;
                if (rentalDays > 3)
                    thisAmount += (rentalDays - 3) * 1.5;
                break;
        }
        return thisAmount;
    }
}