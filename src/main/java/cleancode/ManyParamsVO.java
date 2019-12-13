package cleancode;

import static java.util.Objects.requireNonNull;

public class ManyParamsVO {
    public static void main(String[] args) {
        FullName fullName = new FullName("John", "Doe");
        new ManyParamsVO().placeOrder(fullName, "St. Albergue", "Paris", 99);
    }
    public void placeOrder(FullName fullName, String city, String streetName, Integer streetNumber) {
        requireNonNull(fullName);
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

    public String asFull() {
        return firstName + " " + lastName.toUpperCase();
    }
}



class AnotherClass {
    public void otherMethod(FullName fullName, int x) {
        requireNonNull(fullName);

    	System.out.println("Another distant Logic");
    }
}

// Entity . holy stuff
class Person {
    private Long id;
    private FullName fullName;
    private String phone;

    public Person(String firstName, String lastName) {
        this.fullName = new FullName(firstName, lastName);
        // TODO think: is this sufficient enforcing ?
    }

    public FullName getFullName() {
        return fullName;
    }

}

class PersonService {
    public void f(Person person) {
        System.out.println(person.getFullName().asFull());
    }

    public void p(String city, String streetName, Integer streetNumber) {
        System.out.println("Living in " + city + " on St. " + streetName + " " + streetNumber);
    }
}