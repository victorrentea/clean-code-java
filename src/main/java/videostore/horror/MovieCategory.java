package videostore.horror;

public enum MovieCategory {
    CHILDRENS {
        @Override
        double computePrice(int daysRented) {
            double price = 1.5;
            if (daysRented > 3)
                price += (daysRented - 3) * 1.5;
            return price;
        }
    }, REGULAR {
        @Override
        double computePrice(int daysRented) {
            double price = 2;
            if (daysRented > 2)
                price += (daysRented - 2) * 1.5;
            return price;
        }
    }, NEW_RELEASE {
        @Override
        double computePrice(int daysRented) {
            return daysRented * 3;
        }
    };

    abstract double computePrice(int daysRented);
}
