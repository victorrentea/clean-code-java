package victor.training.refactoring;

import java.util.Arrays;
import java.util.List;

public class ImmutablePlay {
    public static void main(String[] args) {
        Immutable immutable = new Immutable();
        immutable.x = 2;
        immutable.numbers = Arrays.asList(1, 2, 3, 4, 5);
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
