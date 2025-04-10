package victor.training.cleancode.kata.videostore;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
class Rental {
    private final Movie movie;
    private final Integer daysRented;

    public Rental(Movie movie, Integer daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public Movie getMovie() {
        return this.movie;
    }

    public Integer getDaysRented() {
        return this.daysRented;
    }

    public Double getRentalPrice(){
        double rentalPrice = 0;

        switch (this.getMovie().getMovieType()) {
            case Movie.REGULAR:
                rentalPrice += 2;
                if (daysRented > 2)
                    rentalPrice += (daysRented - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                rentalPrice += daysRented * 3;
                break;
            case Movie.CHILDREN:
                rentalPrice += 1.5;
                if (daysRented > 3)
                    rentalPrice += (daysRented - 3) * 1.5;
                break;
        }

        return rentalPrice;
    }
}

class Customer {
    private final String name;
    private final List<Rental> rentals = new ArrayList<>(); // preserves order of elements TODO find a better way to store this

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Movie movie, int daysRented) {
        rentals.add(new Rental( movie, daysRented ));
    }

    public String getName() {
        return name;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + name + "\n";
        // loop over each movie rental
        for (Rental rentedMovie : rentals) {
            double rentalPrice = rentedMovie.getRentalPrice();
            /*// determine amounts for every line
            int daysRented = rentals.get(rentedMovie);
            switch (rentedMovie.getMovie().getMovieType()) {
                case Movie.REGULAR:
                    rentalPrice += 2;
                    if (daysRented > 2)
                        rentalPrice += (daysRented - 2) * 1.5;
                    break;
                case Movie.NEW_RELEASE:
                    rentalPrice += daysRented * 3;
                    break;
                case Movie.CHILDREN:
                    rentalPrice += 1.5;
                    if (daysRented > 3)
                        rentalPrice += (daysRented - 3) * 1.5;
                    break;
            }*/
            frequentRenterPoints = computeFrequentRenterPoints(rentedMovie, frequentRenterPoints);
            // show figures line for this rental
            result += "\t" + rentedMovie.getMovie().getTitle() + "\t" + rentalPrice + "\n";
            totalAmount += rentalPrice;
        }
        // add footer lines
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";
        return result;
    }

    private static int computeFrequentRenterPoints(Rental rentedMovie, int frequentRenterPoints) {
        // add frequent renter points
        frequentRenterPoints++;
        int daysRented = rentedMovie.getDaysRented();
        // add bonus for a two days new release rental
        if (rentedMovie.getMovie().getMovieType() != null &&
                (rentedMovie.getMovie().getMovieType() == Movie.NEW_RELEASE)
                && daysRented > 1)
            frequentRenterPoints++;
        return frequentRenterPoints;
    }
}