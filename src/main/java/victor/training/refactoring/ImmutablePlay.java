package victor.training.refactoring;

import lombok.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

public class ImmutablePlay {
    public static void main(String[] args) {
        List<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toList());

        Immutable immutable = new Immutable(2, numbers, new Other(13));

        numbers.clear();

//        immutable.getNumbers().clear();
        for (Integer number : immutable.getNumbers()) {
            System.out.println(number);
        }

        immutable = immutable.withX(3);

        System.out.println(immutable);
    }

}
@Value
class Immutable {
    @With
    int x;
    List<Integer> numbers;
    @NonNull
    Other other;

//    public Iterable<Integer> getNumbers() { // geek zone <1%
//        return numbers;
//    }
    public List<Integer> getNumbers() { // the most common 95%
        return unmodifiableList(numbers);
    }
//    public List<Integer> getNumbers() { // 4%
//        return new ArrayList<>(numbers);
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
