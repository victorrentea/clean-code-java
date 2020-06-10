package victor.training.refactoring;

import java.util.*;

import static java.util.Collections.*;

public class ImmutablePlay {
    public static void main(String[] args) {
        Other other = new Other(13);
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Immutable immutable = new Immutable(2, numbers, other);

        numbers.add(7);
        System.out.println(immutable.getX());
//        immutable.getNumbers().add(7);
        System.out.println(immutable.getNumbers());
        for (Integer number : immutable.getNumbers()) {
            System.out.println(number);
        }

//        Set<Child> children = new HashSet<>();
//        Child childOne = new Child("Emma");
//        children.add(childOne);
//        System.out.println(children.contains(childOne));
    }
}

class Immutable {
    private final int x;
    private final List<Integer> numbers;
    private final Other other;

    Immutable(int x, List<Integer> numbers, Other other) {
        this.x = x;
        this.numbers = new ArrayList<>(numbers);
        this.other = other;
    }

//    public List<Integer> getNumbers() {
//        return unmodifiableList(numbers);
//    }
//    public Iterable<Integer> getNumbers() {
//        return numbers;
//    }
    public Collection<? extends Integer> getNumbers() {
        return numbers;
    }

    public int getX() {
        return x;
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
