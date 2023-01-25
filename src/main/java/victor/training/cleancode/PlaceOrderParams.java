package victor.training.cleancode;

public class PlaceOrderParams {
  private final String fName;
  private final String lName;
  private final String city;
  private final String streetName;
  private final Integer streetNumber;

  public PlaceOrderParams(String fName, String lName, String city, String streetName, Integer streetNumber) {
    this.fName = fName;
    this.lName = lName;
    this.city = city;
    this.streetName = streetName;
    this.streetNumber = streetNumber;
  }

  public String getfName() {
    return fName;
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
