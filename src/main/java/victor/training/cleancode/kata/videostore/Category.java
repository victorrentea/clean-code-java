package victor.training.cleancode.kata.videostore;

import lombok.Getter;

public enum Category {
    CHILDREN(2),
    REGULAR(0),
    NEW_RELEASE(1);

    @Getter
    private final int code;


    Category(int value) {
        this.code = value;
    }


}