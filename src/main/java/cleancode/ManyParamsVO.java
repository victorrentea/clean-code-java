package cleancode;

import lombok.Data;

public class ManyParamsVO {
    public static void main(String[] args) {
        new ManyParamsVO().placeOrder(new FullName("John", "Doe"), new PersonAddress("St. Albergue", "Paris", 99));
    }
    public void placeOrder(FullName fullName, PersonAddress address) {
    	if (fullName == null)
    	    throw new IllegalArgumentException();
    	
    	System.out.println("Some Logic");
    }
}

class FullName {
    private final String firstName;
    private final String lastName;

    public FullName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        if (firstName == null || lastName == null) {
            throw new IllegalArgumentException();
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAsFullName() {
        return firstName + " " + lastName;
    }
}
@Data
class PersonAddress {
    private final String city;
    private final String streetName;
    private final Integer streetNumber;
}

class AnotherClass {
    public void otherMethod(FullName fullName, int x) {
    	if (fullName == null)
    	    throw new IllegalArgumentException();
    	
    	System.out.println("Another distant Logic");
    }
}

class Person {
    private FullName fullName;

    public Person(FullName fullName) {
        this.fullName = fullName;
        if (fullName == null) throw new IllegalArgumentException();
        // TODO think: is this sufficient enforcing ?
    }

    public Person setFullName(FullName fullName) {
        this.fullName = fullName;
        return this;
    }

    public FullName getFullName() {
        return fullName;
    }

    public String getFirstName() {
        return getFullName().getFirstName();
    }
    public String getLastName() {
        return getFullName().getLastName();
    }
}

class PersonService {
    public void f(Person person) {
        String fullName = person.getFirstName() + " " + person.getLastName().toUpperCase();
        System.out.println(fullName);
    }
}