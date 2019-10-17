package cleancode;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class LoosingElementsInSet {
    public static void main(String[] args) {
        Set<Child> children = new HashSet<>();

        Child childOne = new Child("Emma");
        children.add(childOne);
        System.out.println("hash(copil) = " + childOne.hashCode());
        System.out.println(children.contains(childOne));
        childOne.setName("Emma-Simona");

        System.out.println("hash(copil) = " + childOne.hashCode());
        System.out.println(children.contains(childOne));
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Child child = (Child) o;
        return Objects.equals(name, child.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}