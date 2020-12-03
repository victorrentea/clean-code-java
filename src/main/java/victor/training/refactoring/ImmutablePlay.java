package victor.training.refactoring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutablePlay {
    public static void main(String[] args) {
        List<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toList());
        Immutable immutable = new Immutable(2, numbers, new Other(13));
//        immutable.getNumbers().clear();
        System.out.println(immutable.getX());
        System.out.println(immutable.getNumbers());
        numbers.clear();

        System.out.println(immutable.getNumbers());

        immutable = immutable.withX(3);
        System.out.println(immutable);
    }
}

class Immutable {
    private final int x;
    private final List<Integer> numbers;
    private final Other other;

    Immutable(int x, List<Integer> numbers, Other other) {
        if (numbers == null) {
            throw new IllegalArgumentException();
        }
        this.x = x;
        this.numbers = new ArrayList<>(numbers); // risk mic, nu prea merita
        this.other = other;
    }

    public int getX() {
        return x;
    }
    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
    public Other getOther() {
        return other;
    }

    public Immutable withX(int newX) {
        return new Immutable(newX, numbers, other);
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
