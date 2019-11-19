package cleancode;

import lombok.NonNull;

public class ManyParamsVO {
    public static void main(String[] args) {
        new ManyParamsVO().placeOrder(new FullName("John", "Doe"), "St. Albergue", "Paris", 99);
    }
    public void placeOrder(FullName fullName, String city, String streetName, Integer streetNumber) {
    	if (fullName == null) throw new IllegalArgumentException("null fullName");
    	System.out.println("Some Logic");
    }
}
class FullName {
    private final String firstName;
    private final String lastName;

    public FullName(String firstName, String lastName) {
        if (firstName == null || lastName == null) throw new IllegalArgumentException();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}

class AnotherClass {
    public void otherMethod(FullName fullName, int x) {
    	if (fullName == null) throw new IllegalArgumentException();
    	
    	System.out.println("Another distant Logic");
    }
}

// Holy ENtity!!
class Person {
    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        if (firstName == null || lastName == null) throw new IllegalArgumentException();
        // TODO think: is this sufficient enforcing ?
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
}

class PersonService {
    public void f(Person person) {
        String fullName = person.getFirstName() + " " + person.getLastName().toUpperCase();
        System.out.println(fullName);
    }
}