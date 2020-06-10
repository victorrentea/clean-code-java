package victor.training.refactoring;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ImmutablePlay {
    public static void main(String[] args) {
        Immutable immutable = new Immutable();
        immutable.x = 2;
        immutable.numbers = Arrays.asList(1, 2, 3, 4, 5);
        immutable.other = new Other(13);
        System.out.println(immutable.x);

        Set<Child> children = new HashSet<>();
        Child childOne = new Child("Emma");
        children.add(childOne);
        System.out.println(children.contains(childOne));
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

class Child {
    private String name;

    public Child(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Child setName(String name) {
        this.name = name;
        return this;
    }
}