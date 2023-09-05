package victor.training.cleancode.fp;

import java.util.stream.IntStream;

public class PurpleUnderline {
    private int runThings(int someId) {
        return gimmeStream()
                .map(streamVal -> processInteger(streamVal, someId))
            // e mov pt ca param functiei (negru) devine aici parte din clojure-ul lambdei
            // un fel de camp imutabil care pleaca cu lambda din functie afara.
                .sum();
    }

    IntStream gimmeStream(){
        return IntStream.range(0, 10);
    }

    private Integer processInteger(int val, int someId) {
        return val/someId;
    }
}