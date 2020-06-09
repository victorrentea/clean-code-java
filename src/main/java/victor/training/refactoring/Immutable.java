package victor.training.refactoring;

import java.sql.Connection;
import java.util.*;

class MutableHost { //think "Entity"
    public Immutable immutable;

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        Immutable immutable = new Immutable(3, list, new Other(17));
        list.add(2);
        System.out.println(immutable.getX());
        for (Integer number : immutable.getNumbers()) {
            System.out.println("Elem " + number);
        }
//        immutable.getNumbers().add(3); // nu compileaza datorita genericelor (pt forma geek)
//        immutable.getNumbers().containsAll(1,2,4)
        System.out.println(immutable.getNumbers());

        // cum pierzi copilul la Mall

        Set<Object> puiiMei = new HashSet<>();
        Copil childOne = new Copil("Emma");

        puiiMei.add(childOne);

        System.out.println(childOne.hashCode());
        System.out.println("Unde e copilu? " + puiiMei.contains(childOne));
//        childOne.setName("Emma-Simona"); // hashcode equals trebuie implem doar pe campuri nemodificabile!!
        System.out.println(childOne.hashCode());
        System.out.println("Unde e copilu? " + puiiMei.contains(childOne));
    }
}

class Copil{
    private final String name;
//    String prieten;

    public Copil(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
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

public class Immutable {
    private final int x;
    private final List<Integer> numbers;
    private final Other other;

    public Immutable(int x, List<Integer> numbers, Other other) {
        this.x = x;
        this.numbers = new ArrayList<>(numbers);
        this.other = other;
    }

    public int getX() {
        return x;
    }

//    public List<Integer> getNumbers() { // standard
//        return Collections.unmodifiableList(numbers);
//    }
//    public Iterable<Integer> getNumbers() { // zgarcita
//        return Collections.unmodifiableList(numbers);
//    }
    public Collection<? extends Integer> getNumbers() { // geek
        return numbers;
    }
}

class Other {
    private final int x;

    Other(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }
}
