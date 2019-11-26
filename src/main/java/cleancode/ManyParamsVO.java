package cleancode;

public class ManyParamsVO {
    public static void main(String[] args) {
        PersonName personName = new PersonName("John", "Doe");
        new ManyParamsVO().placeOrder(personName, "St. Albergue", "Paris", 99);
    }
    public void placeOrder(PersonName name, String city, String streetName, Integer streetNumber) {
        if (name == null) throw new IllegalArgumentException();
    	System.out.println("Some Logic");
    }
}
class PersonName {
    private final String firstName;
    private final String lastName;
//    protected PersonName() {} // e un rau necesar, cerut de frameworkuri

    public PersonName(String firstName, String lastName) {
    	if (firstName == null || lastName == null) throw new IllegalArgumentException();
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

class AnotherClass {
    public void otherMethod(PersonName name, int x) {
    	if (name == null) throw new IllegalArgumentException();
    	
    	System.out.println("Another distant Logic");
    }
}

class Person {
    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        if (firstName == null || lastName == null) throw new IllegalArgumentException();
        // TODO think: is this sufficient enforcing ?
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
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
}