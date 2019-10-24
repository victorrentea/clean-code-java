package cleancode;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class ImmutablePlay {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        A a = new A("a", new B(), list);
        list.add("Analytica");

        A aNew = a.setS("new");
        a.getNames().add("me again");
    }
}

class A {
    private final String a;
    private final B b;
    private final List<String> names; //shawarma

    public A(String a, B b, List<String> names) {
        this.a = a;
        this.b = b;
        this.names = new ArrayList<>(names);
    }

    public A setS(String newS) {
    return new A(newS,b,names);
    }

    public List<String> getNames() {
        return unmodifiableList(names);
    }
//    public void addName(String name) {
//        if (isEmpty(name)) throw new IllegalArgumentException();
//        names.add(name);
//    }



    public B getB() {
        return b;
    }

    public String getA() {
        return a;
    }
}
class B {
    public String a;
}