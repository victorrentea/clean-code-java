package victor.training.cleancode;

import java.util.Objects;

public final class Address {
  private final String city;
  private final String streetName;
  private final Integer streetNumber;

  public Address(String city, String streetName, Integer streetNumber) {
    this.city = city;
    this.streetName = streetName;
    this.streetNumber = streetNumber;
  }

  public String city() {
    return city;
  }

  public String streetName() {
    return streetName;
  }

  public Integer streetNumber() {
    return streetNumber;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (obj == null || obj.getClass() != this.getClass()) return false;
    var that = (Address) obj;
    return Objects.equals(this.city, that.city) &&
           Objects.equals(this.streetName, that.streetName) &&
           Objects.equals(this.streetNumber, that.streetNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(city, streetName, streetNumber);
  }

  @Override
  public String toString() {
    return "Address[" +
           "city=" + city + ", " +
           "streetName=" + streetName + ", " +
           "streetNumber=" + streetNumber + ']';
  }

}