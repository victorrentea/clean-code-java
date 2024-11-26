package victor.training.cleancode.kata.videostore;

import lombok.Getter;

@Getter
public enum Category {
    CHILDREN(2, 3, 1.5, 1.5),
    REGULAR(0, 2, 1.5, 2),
    NEW_RELEASE(1, 0, 3, 0);

    private final int code;

    private final int discountFactor;
    private final double multiplicationFactor;
    private final double basePrice;

    Category(int value, int discount, double multiplicationFactor, double basePrice) {
        this.code = value;
        this.discountFactor = discount;
        this.multiplicationFactor = multiplicationFactor;
        this.basePrice = basePrice;
    }


}