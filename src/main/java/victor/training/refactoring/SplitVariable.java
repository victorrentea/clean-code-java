package victor.training.refactoring;

public class SplitVariable {
    public int discount(int inputValue, int quantity) {
        if (inputValue > 50) inputValue = inputValue - 2;
        if (quantity > 100) inputValue = inputValue - 1;
        return inputValue;
    }

}