package victor.training.refactoring;

import lombok.Value;

import java.math.BigDecimal;
import java.util.*;

import static java.util.Objects.requireNonNull;

public class ImmutablePlay {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Immutable immutable = new Immutable(2, numbers, new Other(13));
        numbers.add(6);
//        immutable.getNumbers().add(6);
        System.out.println(immutable.getX());

        for (Integer number : immutable.getNumbers()) {
            System.out.println(number);
        }

        if (immutable.getNumbers().contains(4)) {
            System.out.println("Am luat un 4!");
        }

        immutable = immutable.withX(5);

        System.out.println(immutable.getX());


    }
}

//record Immutable(int x, List<Integer> number, Other other) {}

//@Value/
class Immutable {
    private final int x;
    private final List<Integer> numbers;
    private final Other other;

    public Immutable(int x, List<Integer> numbers, Other other) {
        this.x = x;
        this.numbers = new ArrayList<>(numbers); // frica de colegi. Ce mama lui de proces de recrutare aveti voi acolo ?!
        this.other = requireNonNull(other);
    }

    public int getX() {
        return x;
    }

//    public List<Integer> getNumbers() { // waste of memory
//        return new ArrayList<>(numbers);
//    }
//    public List<Integer> getNumbers() { // runtime (unexpected) crash
//        return Collections.unmodifiableList(numbers);
//    }
//    public Iterable<Integer> getNumbers() { // permiti doar iterarea cu for pe el <--- foloseste asta !
//        return numbers;
//    }
    public List<? extends Integer> getNumbers() { // permiti citirea elementelor dar nu update-ul lor.  creepy.
        return numbers;
    }

    public Other getOther() {
        return other;
    }

    public Immutable withX(int newX) {
        return new Immutable(newX, numbers, other);
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
