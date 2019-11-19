package cleancode;

public class ManyParamsVO {
    public static void main(String[] args) {
        FullName fullName = new FullName("John", "Doe");
        Address address = new Address("St. Albergue", "Paris", 99);
        new ManyParamsVO().placeOrder(fullName, address);
        new ManyParamsVO().placeOrder(fullName, address);
        new ManyParamsVO().placeOrder(fullName, address);
        new ManyParamsVO().placeOrder(fullName, address);
        new ManyParamsVO().placeOrder(fullName, address);
        new ManyParamsVO().placeOrder(fullName, address);
    }
    public void placeOrder(FullName fullName, Address address) {
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

    public String asSingleString() {
        return firstName + " " + lastName.toUpperCase();
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
    private FullName fullName;

    public Person(String firstName, String lastName) {
        fullName = new FullName(firstName,lastName);
        // TODO think: is this sufficient enforcing ?
    }

    public FullName getFullName() {
        return fullName;
    }

}

class PersonService {
    public void f(Person person) {
        String fullName = person.getFullName().asSingleString();
        System.out.println(fullName);
    }
}