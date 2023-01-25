package victor.training.cleancode;

import lombok.Value;

@Value
public class FullName {
  String firstName;
  String lastName;

  public String asCorporateName() {
    return firstName + " " + lastName.toUpperCase();
  }
}
