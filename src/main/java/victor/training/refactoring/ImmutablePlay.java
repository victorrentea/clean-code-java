package victor.training.refactoring;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImmutablePlay {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Immutable immutable = new Immutable(2, list, new Other(13));
        System.out.println(immutable.getNumbers());
//        immutable.getOther().setA("tzeapa!");
//        immutable.getNumbers().add(7);

        for (Integer number : immutable.getNumbers()) {
            System.out.println(number);
        }
        System.out.println(immutable.getNumbers());
//        Field x = Immutable.class.getDeclaredField("x");
//x.setAccessible(true);
//x.set(immutable, -1);
        System.out.println(immutable.getX());


        Immutable immutable1 = immutable.withX(777);

        String s = "asdas";
        BigDecimal bd = BigDecimal.ONE;

        String s1 = s.toUpperCase();

    }
}
//class ALtaNaspa extends Immutable {
//    public int altCamp;
//    ALtaNaspa(int x, List<Integer> numbers, Other other) {
//        super(x, numbers, other);
//    }
//}

class Immutable {
    private final int x;
    private final List<Integer> numbers;
    private final Other other;

    Immutable(int x, List<Integer> numbers, Other other) {
        this.x = x;
        this.numbers = new ArrayList<>(numbers); // waste of memory
        this.other = other;
    }
//    public List<Integer> getNumbers() { -- cea mai comuna
//        return Collections.unmodifiableList(numbers);
//    }
//    public List<Integer> getNumbers() { // supara GCul
//        return new ArrayList<>(numbers);
//    }
    public Iterable<Integer> getNumbers() { // permite doar iterarea
        return numbers;
    }
    public int getX() {
        return x;
    }
    public Immutable withX(int newX) {
        return new Immutable(newX, numbers, other);
    }
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
