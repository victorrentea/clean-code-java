package videostore.horror;

public enum MovieCategory {
    CHILDREN {
        @Override
        double computePrice(int daysRented) {
            double price = 1.5;
            if (daysRented > 3)
                price += (daysRented - 3) * 1.5;
            return price;
        }
    },
    REGULAR {
        @Override
        double computePrice(int daysRented) {
            double amount = 2;
            if (daysRented > 2)
                amount += (daysRented - 2) * 1.5;
            return amount;
        }
    },
    NEW_RELEASE {
        @Override
        double computePrice(int daysRented) {
            return daysRented * 3;
        }
        //    },
        //    ELDERS {
        //        @Override
        //        double computePrice(int daysRented) {
        //            return 0;
        //        }
    };


    abstract double computePrice(int daysRented);
}
