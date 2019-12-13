package cleancode;

import static java.util.Objects.requireNonNull;

public class ManyParamsVO {
    public static void main(String[] args) {
        FullName fullName = new FullName("John", "Doe");
        Address address = new Address("St. Albergue", "Paris", 99);
        new ManyParamsVO().placeOrder(fullName, address);
    }
    public void placeOrder(FullName fullName, Address address) {
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
    public static void main(String[] args) {
        Person person = new Person("J", "D");
        new PersonService().newF(person.getFullName());
    }

    private void newF(FullName fullName) {
        System.out.println(fullName.asFull());
        System.out.println(fullName.asFull());
        System.out.println(fullName.asFull());
        System.out.println(fullName.asFull());
    }

    public void p(Address address) {
        System.out.println("Living in " + address.getCity() + " on St. " + address.getStreetName() + " " + address.getStreetNumber());
    }
}