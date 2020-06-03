package victor.training.cleancode;

public class Address {
  private final String city;
  private final String streetName;
  private final Integer streetNumber;

  public Address(String city, String streetName, Integer streetNumber) {
    this.city = city;
    this.streetName = streetName;
    this.streetNumber = streetNumber;
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
