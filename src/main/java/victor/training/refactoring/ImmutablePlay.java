package victor.training.refactoring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutablePlay {
    public static void main(String[] args) {
        List<Integer> numbers = Stream.of(1, 2, 3, 4, 5).collect(toList());

        Immutable immutable = new Immutable();
        immutable.x = 2;
        immutable.numbers = numbers;
        immutable.other = new Other(13);
        System.out.println(immutable.x);
    }
}

class Immutable {
    public int x;
    public List<Integer> numbers;
    public Other other;
}

class Other {
    private int a;

    public Other(int a) {
        this.a = a;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}
