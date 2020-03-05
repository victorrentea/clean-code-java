package victor.training.cleancode;

import java.util.Optional;

public class NPE {
    public static void main(String[] args) {
        X x = new X();

//        if (x != null && x.getY() != null && x.getY().getZ() != null) {
//        }
        String s = x.getY().flatMap(Y::getZ).flatMap(Z::getName).map(String::toUpperCase).orElse("");
//        String s = x.getY().flatMap(Y::getZ).flatMap(Z::getLastName).map(String::toUpperCase).orElse("");
//        String s = x.getY().flatMap(Y::getZ).flatMap(Z::getName).map(String::toUpperCase).orElse("");
//        String s = x.getY().flatMap(Y::getZ).flatMap(Z::getName).map(String::toUpperCase).orElse("");
//        String s = x.getY().flatMap(Y::getZ).flatMap(Z::getName).map(String::toUpperCase).orElse("");
    }
}


class X {
    Y y;

    public Optional<Y> getY() {
        return Optional.ofNullable(y);
    }
}
class Y {
    Z z;

    public Optional<Z> getZ() {
        return Optional.ofNullable(z);
    }
}
class Z {
    String name;

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }
}
