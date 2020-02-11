package victor.training.cleancode;

import javax.persistence.Embeddable;

@Embeddable
public class FullName {
    private final String firstName;
    private final String lastName;

    public FullName(String firstName, String lastName) {
        if (firstName == null || lastName == null) throw new IllegalArgumentException();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String toNiceString() {
        return firstName + " " + lastName.toUpperCase();
    }
}
