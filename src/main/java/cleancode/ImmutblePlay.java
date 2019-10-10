package cleancode;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class ImmutblePlay {
    public static void main(String[] args) {
        B b = new B("aa");
        List<String> strings = new ArrayList<>();
        A a = new A("s", b, strings);
//        a.getStrings().add("Tzeapa");
        strings.add("Tzeapa de brad");

        System.out.println(a.getStrings());

        A altA = a.withAnotherS("altS");
    }
}

class A {
    private final String s;
    private final B b;
    private final List<String> strings;

    A(String s, B b, List<String> strings) {
        this.s = s;
        this.b = b;
        this.strings = new ArrayList<>(strings);
    }

    public List<String> getStrings() {
        return unmodifiableList(strings);
    }


    public String getS() {
        return s;
    }

    public A withAnotherS(String s) {
        return new A(s, b, strings);
    }
}
class B {
    public final String s;

    B(String s) {
        this.s = s;
    }
}