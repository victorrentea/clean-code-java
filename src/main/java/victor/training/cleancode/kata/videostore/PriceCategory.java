package victor.training.cleancode.kata.videostore;

import static victor.training.cleancode.kata.videostore.Rental.getRentalCost;

public enum PriceCategory {
    CHILDRENS {
        @Override
        double calculateRentalPrice(int daysRented) {
            return getRentalCost(daysRented, 1.5, 3);
        }
    },
    REGULAR {
        @Override
        double calculateRentalPrice(int daysRented) {
            return getRentalCost(daysRented,2, 2);
        }
    },
    NEW_RELEASE {
        @Override
        double calculateRentalPrice(int daysRented) {
            return  daysRented * 3;
        }
    };


    abstract double calculateRentalPrice(int daysRented);
}
