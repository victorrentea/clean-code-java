package victor.training.cleancode;

import lombok.Data;

public class ManyParamsVO {
    public static void main(String[] args) {
        FullName fullName = new FullName("John", "Doe");
        new ManyParamsVO().placeOrder(fullName, "St. Albergue", "Paris", 99);
    }
    public void placeOrder(FullName fullName, String city, String streetName, Integer streetNumber) {

    	System.out.println("Some Logic");
    }
}

@Data
class FullName {
    private final String firstName;
    private final String lastName;

    FullName(String firstName, String lastName) {
        if (firstName == null || lastName == null) throw new IllegalArgumentException();

        this.firstName = firstName;
        this.lastName = lastName;
    }
}

class AnotherClass {
    public void otherMethod(FullName fullName, int x) {

    	System.out.println("Another distant Logic");
    }
}

// Holy Entity
class Person {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        if (firstName == null || lastName == null) throw new IllegalArgumentException();
        // TODO think: is this sufficient enforcing ?
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    // TODO hard-core: implement setter
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
    public void p(String city, String streetName, Integer streetNumber) {
        System.out.println("Living in " + city + " on St. " + streetName + " " + streetNumber);
    }
}