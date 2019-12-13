package cleancode;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ListOfOpt {
    public static void main(String[] args) {
        Optional<List<Long>> no = Optional.empty();
        List<Long> yes = Collections.emptyList();

    }
}
