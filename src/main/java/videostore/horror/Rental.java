package videostore.horror;

public class Rental {
    private final Movie movie;
    private final int daysRented;

    Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public double getPrice() {
        switch (movie.getPriceCode()) {
            case REGULAR:
                return getRegularPrice();
            case NEW_RELEASE:
                return getNewReleasePrice();
            case CHILDREN:
                return getChildrenPrice();
            default:
                throw new IllegalStateException("Unexpected value: " + movie.getPriceCode());
        }
        //        return switch (movie.getPriceCode()) {
        //            case REGULAR -> getRegularPrice();
        //            case NEW_RELEASE -> getNewReleasePrice();
        //            case CHILDREN -> getChildrenPrice();
        //            default -> throw new IllegalStateException("Unexpected value: " + movie.getPriceCode());
        //        };
    }

    private double getChildrenPrice() {
        double price;
        price = 1.5;
        if (daysRented > 3)
            price += (daysRented - 3) * 1.5;
        return price;
    }

    private int getNewReleasePrice() {
        return daysRented * 3;
    }

    private double getRegularPrice() {
        double price;
        price = 2;
        if (daysRented > 2)
            price += (daysRented - 2) * 1.5;
        return price;
    }

    public int getFrequentRenterPoints() {
        int frequentRenterPoints = 1;
        if (isEligibleForBonus()) {
            frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }

    public boolean isEligibleForBonus() {
        return movie.getPriceCode() == PriceCode.NEW_RELEASE && daysRented >= 2;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Movie getMovie() {
        return movie;
    }
}
