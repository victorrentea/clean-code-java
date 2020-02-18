package victor.training.refactoring;

public class SplitVariable {
    public int discount(int inputValue, int quantity) {

        int valueDiscount = inputValue > 50 ? 2 : 0;
        int quantityDiscount = quantity > 100 ? 1 : 0;
        return inputValue - valueDiscount - quantityDiscount;
    }

}