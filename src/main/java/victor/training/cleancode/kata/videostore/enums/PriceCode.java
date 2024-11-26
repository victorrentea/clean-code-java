package victor.training.cleancode.kata.videostore.enums;

import lombok.Getter;

public enum PriceCode {

    CHILDREN(2), REGULAR(0), NEW_RELEASE(1), DEFAULT(-1);

    PriceCode(int value) {
        this.value = value;
    }

    private final int value;

    public int getValue() {
        return this.value;
    }
}
