package victor.training.cleancode.kata.videostore;

public enum PriceCategory {
    CHILDRENS {
        @Override
        double calculateRentalPrice(int daysRented) {
            double rentalCost = 1.5;
            if (daysRented > 3) {
                rentalCost += (daysRented - 3) * 1.5;
            }
            return rentalCost;
        }
    },
    REGULAR {
        @Override
        double calculateRentalPrice(int daysRented) {
            double rentalCost = 2;
            if (daysRented > 2) {
                rentalCost += (daysRented - 2) * 1.5;
            }
            return rentalCost;
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
