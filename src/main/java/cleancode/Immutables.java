package cleancode;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class Immutables {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        A a = new A("a", new B(), list);
        list.add("tzeapa!");
        a.getStrings().add("Tzeapa2");

        A altA = a.setA("new A");
    }
}
class A {
    private final String a;
    private final B b;
    private final List<String> strings;
    public A(String a, B b, List<String> strings) {
        // TODO validari
        this.a = a;
        this.b = b;
        this.strings = new ArrayList<>(strings);
    }
    public List<String> getStrings() {
        return unmodifiableList(strings);
    }
    public String getA() {
        return a;
    }
    public A setA(String newA) {
        return new A(newA, b, strings);
    }
}

class B {
}