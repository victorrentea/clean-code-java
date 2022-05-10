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
//        incrementUsageCounters(feature)
        switch (movie.getCategory()) { // nu ai nimic in afara de switch in functie
            case REGULAR:
                return computeRegularPrice(daysRented); //1 line / case
            case NEW_RELEASE:
                return computeNewReleasePrice(daysRented);
            case CHILDREN:
                return computeChildrenPrice(daysRented);
            default:
                throw new IllegalStateException("Unexpected value: " + movie.getCategory()); // ai default cu throw
        }
    }

    private int computeNewReleasePrice(int dr) {
        return dr * 3;
    }

    private double computeChildrenPrice(int dr) {
        double price;
        price = 1.5;
        if (dr > 3)
            price += (dr - 3) * 1.5;
        return price;
    }

    private double computeRegularPrice(int dr) {
        double price;
        price = 2;// non-idempotenta + Math.random();
        if (dr > 2)
            price += (dr - 2) * 1.5;
        return price;
    }

    public int computeRenterPoints() {
        int frequentRenterPoints = 0;
        frequentRenterPoints++;
        if (getMovie().isNewRelease() && getDaysRented() >= 2) {
            frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }
}
