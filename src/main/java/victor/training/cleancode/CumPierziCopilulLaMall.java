package victor.training.cleancode;

import java.util.*;

public class CumPierziCopilulLaMall {
    public static void main(String[] args) {
        Copil childPoc = new Copil("Emma");

        Set<Copil> puiiMei = new HashSet<>();
        puiiMei.add(childPoc);

        System.out.println(childPoc.hashCode());
        System.out.println(puiiMei.contains(childPoc));

//        childPoc.setName("Emma-Simona");

        System.out.println(childPoc.hashCode());
        System.out.println(puiiMei.contains(childPoc));

    }
}

class Copil{
    private final String name;

//    public Copil() {}
    public Copil(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

//    public Copil setName(String name) {
//        this.name = name;
//        return this;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Copil copil = (Copil) o;
        return Objects.equals(name, copil.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}