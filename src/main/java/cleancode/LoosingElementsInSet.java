package cleancode;

import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class LoosingElementsInSet {
    public static void main(String[] args) {
        Set<Child> children = new HashSet<>();
        Child childOne = new Child("Emma");

        children.add(childOne);
        System.out.println("hash(child)" + childOne.hashCode());
        System.out.println(children.contains(childOne));

//        childOne.setName("Emma-Simona");

        System.out.println("hash(child)" + childOne.hashCode());
        System.out.println(children.contains(childOne));
    }
}

@EqualsAndHashCode
class Child {
    private final String name;

    public Child(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

//    public Child setName(String name) {
//        this.name = name;
//        return this;
//    }
}