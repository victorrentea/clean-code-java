package victor.training.refactoring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.*;

public class ImmutablePlay {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Immutable immutable = new Immutable(2, numbers, new Other(13));
        // dupa instantiere :::::::::
        numbers.add(-1); // asta e hackareala. Nici un dev nu poa sa zica ca a facut din greseala asta.
        // posibil doar in zona instantierii..

        // ori obiectul pasat de colo colo nu va avea langa el si pointer la lista de obiecte initiala.

        System.out.println(immutable.getX());
//        immutable.getNumbers().add(1);

//        immutable.getNumbers().contains(2);

        for (Integer number : immutable.getNumbers()) {
            System.out.println("Eleme"+number );
        }

        System.out.println(immutable.getNumbers());

    }
}

class Immutable {
    private final int x;
    private final List<Integer> numbers;
    private final Other other;
    Immutable(int x, List<Integer> numbers, Other other) {
        this.x = x;
        this.numbers = new ArrayList<>(numbers); // NU RECOMAND asta;
        this.other = other;
    }
    public int getX() {
        return x;
    }

    // RUPI GC
//    public List<Integer> getNumbers() {
//        return new ArrayList<>(numbers);
//    }

    // rau: runtime exception
//    public List<Integer> getNumbers() {
//        return unmodifiableList(numbers);
//    }
    public Iterable<Integer> getNumbers() { // foloseste pe asta
        return numbers;
    }
//    public List<? extends Integer> getNumbers() { // foloseste pe asta
//        return numbers;
//    }

    public Other getOther() {
        return other;
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
