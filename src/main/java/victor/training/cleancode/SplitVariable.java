package victor.training.cleancode;

public class SplitVariable {

    // @see test
    public static int discount(int price, int quantity) {
        if (price > 50) price = price - 2;
        if (quantity > 100) price = price - 1;
        return price;
    }
}