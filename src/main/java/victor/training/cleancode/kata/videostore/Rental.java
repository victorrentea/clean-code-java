package victor.training.cleancode.kata.videostore;

import static victor.training.cleancode.kata.videostore.MovieType.NEW_RELEASE;

public record Rental(Movie movie, Integer daysToRent) {

    public boolean eligibleForBonus() {
        return movie().type() == NEW_RELEASE &&
                daysToRent() >= 2;
    }

    public double calculateAmount() {
        double thisAmount = 0;
        switch (movie().type()) {
            case REGULAR:
                thisAmount += 2;
                if (daysToRent() > 2)
                    thisAmount += (daysToRent() - 2) * 1.5;
                break;
            case NEW_RELEASE:
                thisAmount += daysToRent() * 3;
                break;
            case CHILDREN:
                thisAmount += 1.5;
                if (daysToRent() > 3)
                    thisAmount += (daysToRent() - 3) * 1.5;
                break;
        }
        return thisAmount;
    }

    public String getText() {
        double amountForCurrentRental = this.calculateAmount();
        return "\t" + (this.movie().title()) + "\t" + amountForCurrentRental + "\n";
    }
}
