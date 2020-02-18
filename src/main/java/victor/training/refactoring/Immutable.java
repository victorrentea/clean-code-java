package victor.training.refactoring;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

class MutableHost { //think "Entity"
    public Immutable immutable;

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Other other = new Other();
        Immutable immutable = new Immutable(1, list, other);
        list.add(-1);

        System.out.println(immutable.getNumbers());
        Immutable altaInstanta = immutable.withX(2);
    }
}
public class Immutable {
    private final int x;
    private final List<Integer> numbers;
    private final Other other;

    public Immutable(int x, List<Integer> numbers, Other other) {
        this.x = x;
        this.numbers = new ArrayList<>(numbers);
        this.other = other;
    }
    public int getX() {
        return x;
    }

    public Immutable withX(int newX) {
        return new Immutable(newX, numbers, other);
    }
    public List<Integer> getNumbers() {
        return unmodifiableList(numbers);
    }
    public Other getOther() {
        return other;
    }
}

class Other {}
