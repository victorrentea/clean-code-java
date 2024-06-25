package victor.training.cleancode.kata.videostore;


public record Movie(String title, PriceCode priceCode) {

    double getPrice(int discountRate) {
        double price = 0;
        switch (priceCode) {
            case REGULAR -> {
                price = getPrice(discountRate, price);
            }
            case NEW_RELEASE -> price += discountRate * 3;
            case CHILDREN -> {
                price += 1.5;
                if (discountRate > 3)
                    price += (discountRate - 3) * 1.5;
            }
        }
        return price;
    }

    private static double getPrice(int discountRate, double price) {
        price += 2;
        if (discountRate > 2)
            price += (discountRate - 2) * 1.5;
        return price;
    }
}