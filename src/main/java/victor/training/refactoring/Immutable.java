package victor.training.refactoring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class MutableHost { //think "Entity"
    public Immutable immutable;

    public Immutable getImmutable() {
        return immutable;
    }

    public void setImmutable(Immutable immutable) {
        this.immutable = immutable;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Immutable immutable = new Immutable(1, list, new Other());
        System.out.println(immutable.getNumbers());
        list.add(-1);
        System.out.println(immutable.getNumbers());

//        new MutableHost().getImmutable().setX(12); // pe vremuri
        MutableHost m = new MutableHost();
        m.setImmutable(m.getImmutable().withX(12));

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

    public Immutable addNumber(int n) {
        ArrayList<Integer> newList = new ArrayList<>(numbers);
        newList.add(n);
        return new Immutable(x, newList, other);
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
}

class Other {
    public int x;
}
