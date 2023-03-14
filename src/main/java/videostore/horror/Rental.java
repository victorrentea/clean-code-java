package videostore.horror;

public class Rental {
    private final Movie movie;
    private final int daysRented;

    Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public double getPrice() {
        double price = 0;
        switch (getMovie().getPriceCode()) {
            case REGULAR:
                price += 2;
                if (getDaysRented() > 2)
                    price += (getDaysRented() - 2) * 1.5;
                break;
            case NEW_RELEASE:
                price += getDaysRented() * 3;
                break;
            case CHILDREN:
                price += 1.5;
                if (getDaysRented() > 3)
                    price += (getDaysRented() - 3) * 1.5;
                break;
        }
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
        return getMovie().getPriceCode() == PriceCode.NEW_RELEASE && getDaysRented() >= 2;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Movie getMovie() {
        return movie;
    }
}
