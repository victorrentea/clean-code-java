package victor.training.cleancode.kata.videostore;

import lombok.AllArgsConstructor;

//TODO rename enum
@AllArgsConstructor
public enum MovieEnum {
    CHILDRENS(2),
    REGULAR(0),
    NEW_RELEASE(1);

    int type;
}
