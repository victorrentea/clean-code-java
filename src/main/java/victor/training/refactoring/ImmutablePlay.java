package victor.training.refactoring;

import lombok.Value;
import lombok.With;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutablePlay {
    public static void main(String[] args) {
        List<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toList());

        Immutable immutable = new Immutable(2, numbers, new Other(13));
        numbers.clear();
//        immutable.getNumbers().clear();

        immutable = immutable.withX(6);

        for (Integer number : immutable.getNumbers()) {
            System.out.println(number);
        }

        System.out.println(immutable);
    }
}

@Value
class Immutable {
//    @With
    int x;
    List<Integer> numbers;
    Other other;

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    public Immutable withX(int newX) {
        return new Immutable(newX, numbers, other);
    }
//    public Iterable<Integer> getNumbers() {
//        return numbers;
//    }

}

class Other {
    private final int a;

    public Other(int a) {
        this.a = a;
    }

    public int getA() {
        return a;
    }

}
