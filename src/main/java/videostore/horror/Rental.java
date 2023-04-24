package videostore.horror;

public record Rental(Movie movie, int daysRented) {
    public int computeBonusPoints() {
        int frequentRenterPoints = 1;
        if (movie().category() == MovieCategory.NEW_RELEASE && daysRented() >= 2)
            frequentRenterPoints++;
        return frequentRenterPoints;
    }

    public double computePrice() {
        return movie().category().computePrice(daysRented());
    }
}
