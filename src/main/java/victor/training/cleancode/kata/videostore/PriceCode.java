package victor.training.cleancode.kata.videostore;

public enum PriceCode {
    CHILDREN {
        @Override
        public double calculatePrice(int daysRented) {
            double amount = 1.5;
            if (daysRented > 3) {
                amount += (daysRented - 3) * 1.5;
            }
            return amount;
        }
    },
    REGULAR {
        @Override
        public double calculatePrice(int daysRented) {
            double amount = 2;
            if (daysRented > 2) {
                amount += (daysRented - 2) * 1.5;
            }
            return amount;
        }
    },
    NEW_RELEASE {
        @Override
        public double calculatePrice(int daysRented) {
            return daysRented * 3;
        }
    };


    public abstract double calculatePrice(int daysRented);
}
