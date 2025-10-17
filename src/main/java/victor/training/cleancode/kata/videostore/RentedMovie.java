package victor.training.cleancode.kata.videostore;

public record RentedMovie(Movie movie, int rentalDays) {
    public double computeRentalPrice() {
        double thisAmount = 0;
        // determine amounts for every line
        switch (moviePriceCode()) {
            case REGULAR:
                thisAmount += 2;
                if (rentalDays() > 2)
                    thisAmount += (rentalDays() - 2) * 1.5;
                break;
            case NEW_RELEASE:
                thisAmount += rentalDays() * 3;
                break;
            case CHILDREN:
                thisAmount += 1.5;
                if (rentalDays() > 3)
                    thisAmount += (rentalDays() - 3) * 1.5;
                break;
        }
        return thisAmount;
    }

    public String formatForInvoice() {
        return "\t" + movie().title() + "\t" + computeRentalPrice() + "\n";
    }

    PriceCode moviePriceCode() {
        return movie.priceCode();
    }
}
