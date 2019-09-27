package cleancode;

public class ManyParamsVO {
    public static void main(String[] args) {
        new ManyParamsVO().placeOrder(new FullName("John", "Doe"),
        		"St. Albergue", "Paris", 99);
    }
    public void placeOrder(FullName fullName, String city, String streetName, Integer streetNumber) {
    	if (fullName == null) {
    		throw new IllegalArgumentException();
    	}
    }
}

//class Person {
//	@Embeddable 
//	FullName fullname; // game WON! +1% salariu
//}

class StreetAddress {
	
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
	
}



class AnotherClass {
    public void otherMethod(FullName fullName, int x) {
        //distant logic
    }
}



