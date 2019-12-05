package videostore.dirty;

import cleancode.pretend.Service;

class Rental {
    private final Movie movie;
    private final int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public double computePrice() {
        return new PriceCalculationService().computePrice(this);
    }


	public int computeFrequentRenterPoints() {
        int frequentRenterPoints = 1;
        boolean isNewRelease = movie.getType() == Movie.Type.NEW_RELEASE;
        if (isNewRelease && daysRented >= 2) {
            frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }

    public int getDaysRented() {
        return daysRented;
    }
}

@Service
class PriceCalculationService {

    public double computePrice(Rental rental) {
        return rental.getMovie().getType().
                priceAlgo.apply(this, rental.getDaysRented());
    }

    public double computeChildrenPrice(int daysRented) {
        double price = 1.5;
        if (daysRented > 3)
            price += (daysRented - 3) * 1.5;
        return price;
    }

    public double computeNewReleasePrice(int daysRented) {
        return daysRented * 3;
    }

    public double computeRegularPrice(int daysRented) {
        double price = 0;
        price += 2;
        if (daysRented > 2)
            price += (daysRented - 2) * 1.5;
        return price;
    }
}