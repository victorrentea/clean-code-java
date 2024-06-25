package victor.training.cleancode.kata.videostore;

enum PriceCode {
    REGULAR {
        @Override
        public double getPrice(int discountRate) {
            if (discountRate <= 2) {
                return 2;
            }
            return 2 + (discountRate - 2) * 1.5;
        }
    },

    NEW_RELEASE {
        @Override
        public double getPrice(int discountRate) {
            return discountRate * 3;
        }

        @Override
        public double getFrequentRenterPoints(int discountRate) {
            if (discountRate > 1){
                return 2;
            }
            return 1;
        }
    },

    CHILDREN {
        @Override
        public double getPrice(int discountRate) {
            if (discountRate <= 3) {
                return 1.5;
            }
            return 1.5 + (discountRate - 3) * 1.5;
        }
    };

    public abstract double getPrice(int discountRate);

    public double getFrequentRenterPoints(int discountRate){
        return 1;
    }
}
