package victor.training.refactoring;

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
class Immutable {
    private final int x;
    private final List<Integer> numbers;
    private final Other other;

    Immutable(int x, List<Integer> numbers, Other other) {
        this.x = x;
//        this.numbers =  Arrays.asList(numbers.toArray(new Integer[0])); // too much to do!! malloc +
        this.numbers =  numbers;
        this.other = Objects.requireNonNull(other);
    }

    public Immutable withX(int x) { //withers
        return new Immutable(x, numbers, other); // Feature Envy
    }

    public int getX() {
        return x;
    }
//    public Iterable<Integer> getNumbers() { // geek zone <1%
//        return numbers;
//    }
    public List<Integer> getNumbers() { // the most common 95%
        return unmodifiableList(numbers);
    }
//    public List<Integer> getNumbers() { // 4%
//        return new ArrayList<>(numbers);
//    }
    public Other getOther() {
        return other;
    }

    @Override
    public String toString() {
        return "Immutable{" +
               "x=" + x +
               ", numbers=" + numbers +
               ", other=" + other +
               '}';
    }
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
