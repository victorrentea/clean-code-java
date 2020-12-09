package victor.training.refactoring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutablePlay {
    public static void main(String[] args) {
        List<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toList());
        Other other = new Other(13);

        Immutable immutable = new Immutable(2, numbers, other);
        // mai jos.... cum poti modifica starea instantei
//        immutable.getNumbers().clear();

//        numbers.clear();
//        immutable.getOther().setA(-3);

        System.out.println(immutable);

//        immutable = immutable.withX(3);
    }
}

class Immutable {
    private final int x;
    private final List<Integer> numbers;
    private final Other other;
    public Immutable(int x, List<Integer> numbers, Other other) {
        this.x = x;
        this.numbers = numbers;
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

    @Override
    public String toString() {
        return "Immutable{" +
               "x=" + x +
               ", numbers=" + numbers +
               ", other=" + other +
               '}';
    }

    public Immutable withX(int newX) {
        return new Immutable(newX, numbers, other);
    }
//    public Immutable withAddedString(String s) {
//        new ArrayLIst<>
//        return new
//    }
}

class Other {
    private int a;

    public Other(int a) {
        this.a = a;
    }

    public int getA() {
        return a;
    }

}
