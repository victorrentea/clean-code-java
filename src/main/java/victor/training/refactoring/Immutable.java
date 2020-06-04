package victor.training.refactoring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.unmodifiableList;

class MutableHost { //think "Entity"
    public Immutable immutable;

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        Immutable immutable = new Immutable(2, list, new Other());
        list.add(12);
        System.out.println(immutable.getX());
        immutable.getNumbers().add(17);
    }
}


public /*final*/ class Immutable {
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

    public Other getOther() {
        return other;
    }

//    public void addNumber(Integer integer) {
//        numbers.add(integer);
//    }
    public List<Integer> getNumbers() {
        return unmodifiableList(numbers);
    }
}

class Other {}
