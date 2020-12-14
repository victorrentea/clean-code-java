package victor.training.refactoring;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

//class ChangeProofBuffer {
//    ByteBuffer buf1;
//    ByteBuffer safeCopy;
//
//    getData () {
//        if (buf1 != safeCopy) throw
//    }
//}

public class ImmutablePlay {
    public static void main(String[] args) {
        List<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toList());
        Other other = new Other(13);

        Immutable immutable = new Immutable(2, numbers, other);

        immutable = immutable.withX(4);

//        Object o = new ByteBuffer();

        numbers.clear();

//        String a = "a";
//        a.to


//        immutable.getNumbers().add(2);
        for (Integer number : immutable.getNumbers()) {
            System.out.println(number);
        }
        if (immutable.getNumbers().contains(1)) {
            System.out.println("Hooray");
        }
        System.out.println(immutable);
    }
}

class Immutable {
    private final int x;
    private final List<Integer> numbers;
    private final Other other;

    Immutable(int x, List<Integer> numbers, Other other) {
//        assert (); -D
        if (x < 0) {
            throw new IllegalArgumentException();
        }
        this.x = x;
        this.numbers = new ArrayList<>(numbers);
        this.other = Objects.requireNonNull(other);
    }
    public int getX() {
        return x;
    }

    {
        List<Immutable> list = asList(new Immutable(3, asList(1, 2, 3, -1), new Other(2)),
            new Immutable(3, asList(1, 2, 3, -1), new Other(2)),
            new Immutable(3, asList(1, 2, 3, -1), new Other(2)),
            new Immutable(3, asList(1, 2, 3, -1), new Other(2)),
            new Immutable(3, asList(1, 2, 3, -1), new Other(2)),
            new Immutable(3, asList(1, 2, 3, -1), new Other(2)),
            new Immutable(3, asList(1, 2, 3), new Other(2)),
            new Immutable(3, asList(1, 2, 3, -1), new Other(2)));
        List<Immutable> collect = list.stream().filter(Immutable::hasNegativeNumber).collect(toList());

    }

    public boolean hasNegativeNumber() {
        return numbers.stream().anyMatch(e -> e < 0);
    }
//    public List<Integer> getNumbers() {
//        return Collections.unmodifiableList(numbers);
//    }
//    public Iterable<Integer> getNumbers() {
//        return numbers;
//    }
    public List<? extends Integer> getNumbers() {
        return numbers;
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
}

@Value
class Other {
    int a;
}
