package cleancode;

public class ManyParamsVO {
    public static void main(String[] args) {
        PersonName personName = new PersonName("John", "Doe");
        Address address = new Address("St. Albergue", "Paris", 99);
        new ManyParamsVO().placeOrder(personName, address);
    }
    public void placeOrder(PersonName personName, Address address) {
        if (personName == null) {
            throw new IllegalArgumentException("Nu");
        }
    	System.out.println("Some Logic");
    }
}

class PersonName {
    private final String firstName;
    private final String lastName;
    //language=sql
    public static final String SQL = "SELECT * FROM ";

    public PersonName(String firstName, String lastName) {
    	if (firstName == null || lastName == null) {
    	    throw new IllegalArgumentException();
        }
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String asFullName() {
        return firstName + " " + lastName.toUpperCase();
    }
}

class AnotherClass {
    public void otherMethod(PersonName personName, int x) {
    	if (personName == null) {
    	    throw new IllegalArgumentException();
        }
    	
    	System.out.println("Another distant Logic");
    }
}

class Person {
    private PersonName personName;
//    private String firstName;
//    private String lastName;
    private String cnp;
    private String phone;

    public Person(String firstName, String lastName) {
        this.personName = new PersonName(firstName, lastName);
        // TODO think: is this sufficient enforcing ?
    }

    public PersonName getPersonName() {
        return personName;
    }

}

class PersonService {
    public void f(Person person) {
        String fullName = person.getPersonName().asFullName();
        System.out.println(fullName);
    }

}