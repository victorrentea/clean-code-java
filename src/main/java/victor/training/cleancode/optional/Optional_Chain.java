package victor.training.cleancode.optional;

import java.util.Optional;

public class Optional_Chain {
  private static final MyMapper mapper = new MyMapper();

  public static void main(String[] args) {
    var i = 1;
    g(1);
    Parcel parcel = new Parcel();
    parcel.setDelivery(new Delivery(new Address(new ContactPerson("John"))));
    //    parcel.setDelivery(new Delivery(new Address(null)));
    //    parcel.setDelivery(null);

    DeliveryDto dto = mapper.convert(parcel);
    System.out.println(dto);
  }

  private static int f() { // reflection
    return 1;
  }

  public static int g(int x) { // reflection
    return 1;
  }
}


class MyMapper {
  public DeliveryDto convert(Parcel parcel) {
    DeliveryDto dto = new DeliveryDto();
    dto.recipientPerson = parcel.getDelivery().getAddress()
        .getContactPerson().orElseThrow().getName().toUpperCase();
    return dto;
  }
}

class DeliveryDto {
  public String recipientPerson;
}

class Parcel {
  private Delivery delivery; // NULL until a delivery is scheduled

  public Delivery getDelivery() {
    return delivery;
  }

  public void setDelivery(Delivery delivery) {
    this.delivery = delivery;
  }
}


class Delivery {
  private Address address; // NOT NULL IN DB

  public Delivery(Address address) {
    this.address = address;
  }

  public void setAddress(Address address) {
    this.address = address; // TODO null safe
  }

  public Address getAddress() {
    return address;
  }
}

class Address {
  private final ContactPerson contactPerson; // can be null if shipping to a company

  public Address(ContactPerson contactPerson) {
    this.contactPerson = contactPerson;
  }

  public Optional<ContactPerson> getContactPerson() {
    return Optional.ofNullable(contactPerson);
  }
}

class ContactPerson {
  private final String name; // NOT NULL

  public ContactPerson(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
