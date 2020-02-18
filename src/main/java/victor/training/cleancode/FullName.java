package victor.training.cleancode;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Embeddable;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Embeddable
public class FullName {
    private final String firstName;
    private final String lastName;

    public FullName(/*@NonNull */String firstName, @NonNull String lastName) {
        requireNonNull(firstName);
        requireNonNull(lastName);
//        if (firstName == null || lastName == null) {
//            throw new IllegalArgumentException();
//        }
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public String asEnterpriseName() {
        return firstName + " " + lastName.toUpperCase();
    }
}
