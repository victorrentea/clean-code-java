package victor.training.cleancode.kata.videostore;

public record Movie(String title, PriceCode priceCode) {
    enum PriceCode {
        REGULAR {
            @Override
            public double calculateAmount(int daysRented) {
                double thisAmount = 2;
                if (daysRented > 2) {
                    thisAmount += (daysRented - 2) * 1.5;
                }
                return thisAmount;
            }
        },
        NEW_RELEASE {
            @Override
            public double calculateAmount(int daysRented) {
                return daysRented * 3;
            }
        },
        CHILDRENS {
            @Override
            public double calculateAmount(int daysRented) {
                double thisAmount = 1.5;
                if (daysRented > 3) {
                    thisAmount += (daysRented - 3) * 1.5;
                }
                return thisAmount;
            }
        };

        public abstract double calculateAmount(int daysRented);
    }
}