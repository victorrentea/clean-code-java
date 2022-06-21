package victor.training.cleancode;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
// NO, too specific
public class PlaceOrderRequest {
    private final String firstName;
    private final String lName;
    private final String city;
    private final String streetName;
    private final Integer streetNumber;


    public String getFirstName() {
        return firstName;
    }

    public String getlName() {
        return lName;
    }

    public String getCity() {
        return city;
    }

    public String getStreetName() {
        return streetName;
    }

    public Integer getStreetNumber() {
        return streetNumber;
    }
}
