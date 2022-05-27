package videostore.horror;

import java.util.Objects;

public class Rental {
    private final Movie movie;
    private final int daysRented;
//    private final double price; // temporary field ca valoarea din price poate fi calculata on the fly de fiecare data

    Rental(Movie movie, int daysRented) {
        this.movie = Objects.requireNonNull(movie);
        this.daysRented = daysRented;
//        price = calculatePrice();
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public int calculateRenterPoints() {
        int frequentRenterPoints = 1;
        if (deservesBonus()) {
            frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }

    private boolean deservesBonus() {
        return movie.getCategory() == Movie.Category.NEW_RELEASE &&
                daysRented >= 2;
    }

    public double calculatePrice() {
        int daysRented = getDaysRented();

//        return switch (movie.getCategory()) {
//            case REGULAR -> calculateRegularPrice(daysRented);
//            case NEW_RELEASE -> calculateNewReleasePrice(daysRented);
//            case CHILDREN -> calculateChildrenPrice(daysRented);
////            default -> throw new IllegalStateException("Unexpected value: " + getMovie().getCategory());
//        };
//        switch (getMovie().getCategory()) {
//            case REGULAR:
//                return calculateRegularPrice(daysRented);
//            case NEW_RELEASE:
//                return calculateNewReleasePrice(daysRented);
//            case CHILDREN:
//                return calculateChildrenPrice(daysRented);
//            default:
//                throw new IllegalStateException("Unexpected value: " + getMovie().getCategory());
//        }
        return movie.getCategory().computePrice(daysRented);
    }

    private int calculateNewReleasePrice(int daysRented) {
        return daysRented * 3;
    }

    private double calculateChildrenPrice(int daysRented) {
        double price;
        price = 1.5;
        if (daysRented > 3)
            price += (daysRented - 3) * 1.5;
        return price;
    }

    private double calculateRegularPrice(int daysRented) {
        double price;
        price = 2;
        if (daysRented > 2)
            price += (daysRented - 2) * 1.5;
        return price;
    }
//
//    public double getPrice() {
//        return price;
//    }
}
