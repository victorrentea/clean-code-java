package cleancode;

public class ManyParamsVO {
    public static void main(String[] args) {
        AddressVO address = new AddressVO("St. Albergue", "Paris", 99);
        FullName fullName = new FullName("John", "Doe");
        new ManyParamsVO().placeOrder(fullName, address);
    }
    public void placeOrder(FullName fullName, AddressVO address) {
        if (fullName == null) throw new IllegalArgumentException();
    	
    	System.out.println("Some Logic");
    }
}
class FullName {
    private final String firstName;
    private final String lastName;

    public FullName(String firstName, String lastName) {
        if (firstName == null || lastName == null)
            throw new IllegalArgumentException();
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


class AddressVO {
    private final String cityName;
    private final String streetName;
    private final int streetNumber;

    AddressVO(String cityName, String streetName, int streetNumber) {
        this.cityName = cityName;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
    }
}














class AnotherClass {
    public void otherMethod(FullName fullName, int x) {
    	if (fullName == null) throw new IllegalArgumentException();
    	
    	System.out.println("Another distant Logic");
    }
}