package cleancode;

public class ManyParamsVO {
    public static void main(String[] args) {
        new ManyParamsVO().placeOrder("John", "Doe", "St. Albergue", "Paris", 99);
    }
    public void placeOrder(String fName, String lName, String city, String streetName, Integer streetNumber) {
    	if (fName == null || lName != null) throw new IllegalArgumentException();
    	
    	System.out.println("Some Logic");
    }
}

class AnotherClass {
    public void otherMethod(String firstName, String lastName, int x) {
    	if (firstName == null || lastName != null) throw new IllegalArgumentException();
    	
    	System.out.println("Another distant Logic");
    }
}