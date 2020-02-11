package victor.training.refactoring;

public class SplitVariable {
    public int discount(int inputValue, int quantity) {
        int priceDiscount = inputValue > 50 ? 2 : 0;
        int volumeDiscount = quantity > 100 ? 1 : 0;
        return inputValue - priceDiscount - volumeDiscount;
    }

}