package victor.training.cleancode.kata.videostore;

enum PriceCode {
    REGULAR{
        @Override
        public double getDiscountFactor(int discountRate) {
            if (discountRate <= 2) {
                return 2;
            }
            return 2 + (discountRate - 2) * 1.5;
        }
    },NEW_RELEASE{
        @Override
        public double getDiscountFactor(int discountRate) {
            return discountRate * 3;
        }
    }, CHILDREN{
        @Override
        public double getDiscountFactor(int discountRate) {
            if (discountRate <= 3) {
                return 1.5;
            }
            return 1.5 + (discountRate - 3) * 1.5;
        }
    };

    public abstract double getDiscountFactor(int discountRate) ;



//
//    private static double getPrice(int discountRate, double price) {
//        price += 2;
//        if (discountRate > 2)
//            price += (discountRate - 2) * 1.5;
//        return price;
//    }
}
