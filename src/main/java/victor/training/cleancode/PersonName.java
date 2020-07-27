package victor.training.cleancode;

public class PersonName {
	private String firstName;
	private String lastName;

	public PersonName(String firstName, String lastName) {
		if (firstName == null || lastName == null) {
			throw new IllegalArgumentException();
		}
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFullName() {
		return firstName + " " + lastName.toUpperCase();
	}
}