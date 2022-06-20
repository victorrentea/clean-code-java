package videostore.horror;

public class Rental {
    private final Movie movie;
    private final int daysRented;

    Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public double computePrice() {
        switch (movie.getPriceCode()) {
            case REGULAR:
                return computeRegularPrice(daysRented);
            case NEW_RELEASE:
                return computeNewReleasePrice(daysRented);
            case CHILDREN:
                return computeChildrenPrice(daysRented);
            default:
                throw new IllegalStateException("Unexpected value: " + movie.getPriceCode());
        }
        // day-dream: Java 17
//		return switch (each.getPriceCode()) {
//			case REGULAR -> computeRegularPrice(daysRented);
//			case NEW_RELEASE -> computeNewReleasePrice(daysRented);
//			case CHILDREN -> computeChildrenPrice(daysRented);
//			default -> throw new IllegalStateException("Unexpected value: " + each.getPriceCode());
//		};
    }

    private double computeChildrenPrice(int daysRented) {
        double price = 1.5;
        if (daysRented > 3) {
            price += (daysRented - 3) * 1.5;
        }
        return price;
    }

    private int computeNewReleasePrice(int daysRented) {
        return daysRented * 3;
    }

    private double computeRegularPrice(int daysRented) {
        double price = 2;
        if (daysRented > 2) {
            price += (daysRented - 2) * 1.5;
        }
        return price;
    }

    public int computeRenterPoints() {
        int frequentRenterPoints = 1;
        if (getMovie().getPriceCode() == Movie.PriceCode.NEW_RELEASE && getDaysRented() >= 2)
            frequentRenterPoints++;
        return frequentRenterPoints;
    }
}
