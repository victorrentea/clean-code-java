package cleancode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Immutability {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        A a = new A("a", new B(), list);

        list.add("Whack it");

        List<? extends String> strings = a.getStrings();
//        strings.add("Again");
    }
}


class A {
    private final String s;
    private final B b;
    private final List<String> strings;

    public A(String s, B b, List<String> strings) {
        this.s = s;
        this.b = b;
        this.strings = new ArrayList<>(strings);
    }
    public A setS(String newS) {
        return new A(newS, b, strings);
    }

    public List<? extends String> getStrings() {
        return Collections.unmodifiableList(strings);
    }

    public String getS() {
        return s;
    }

    public B getB() {
        return b;
    }
}
class B {


}

