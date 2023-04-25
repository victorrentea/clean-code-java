package videostore.horror;

public record Rental(Movie movie, int daysRented) {
    public int computeBonusPoints() {
        int frequentRenterPoints = 1;
        boolean isNewRelease = movie().category() == MovieCategory.NEW_RELEASE;
        if (isNewRelease && daysRented() >= 2) {
            frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }

    public double computePrice() {
//        return movie().category().computePrice(daysRented());
        return switch (movie.category()) {
            case REGULAR -> computeRegularPrice(daysRented);
            case CHILDRENS -> computeChildrenPrice(daysRented);
            case NEW_RELEASE -> computeNewReleasePrice(daysRented);
        };
    }

    double computeChildrenPrice(int daysRented) {
        double price = 1.5;
        if (daysRented > 3)
            price += (daysRented - 3) * 1.5;
        return price;
    }
    double computeRegularPrice(int daysRented) {
        double price = 2;
        if (daysRented > 2)
            price += (daysRented - 2) * 1.5;
        return price;
    }

    double computeNewReleasePrice(int daysRented) {
        return daysRented * 3;
    }
}
