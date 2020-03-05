package victor.training.cleancode;

import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.*;

public class VreauSaMaJoc {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        A a = new A("a", new B(1), list);

        list.add("tzeapa");

//        a.getStrings().add("de lemn de prun");
        for (String string : a.getStrings()) {

        }
        System.out.println(a);
    }
}

@ToString
class A {
    private final String a;
    private final B b;
    private final List<String> strings;

    public A(String a, B b, List<String> strings) {
        this.a = a;
        this.b = b;
        this.strings = new ArrayList<>(strings);
    }
    public List<? extends String> getStrings() {
        return unmodifiableList(strings);
    }
    public B getB() {
        return b;
    }
    public String getA() {
        return a;
    }
}
class B {
    private final int x;

    B(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

}