package victor.training.cleancode.optional;

import lombok.Getter;

import java.util.Objects;
import java.util.Optional;

public class Optional_Chain {
  private static final MyMapper mapper = new MyMapper();

  public static void main(String[] args) {
    Parcel parcel = new Parcel();
    parcel.setDelivery(new Delivery(new Address(new ContactPerson("John"))));
    //    parcel.setDelivery(new Delivery(new Address(null)));
    //    parcel.setDelivery(null);

    DeliveryDto dto = mapper.convert(parcel);
    System.out.println(dto);
  }
}

class MyMapper {
  public DeliveryDto convert(Parcel parcel) {
    DeliveryDto dto = new DeliveryDto();
//    if (
//        parcel != null &&
//        parcel.getDelivery() != null &&
//        parcel.getDelivery().getAddress() != null &&
//        parcel.getDelivery().getAddress().getContactPerson() != null &&
//        parcel.getDelivery().getAddress().getContactPerson().getName() != null
//    )

//    dto.recipientPerson = parcel.getDelivery().getAddress().getContactPerson().getName().toUpperCase();

    dto.recipientPerson = parcel.getDelivery()
        .map(Delivery::getAddress)
        .flatMap(Address::getContactPerson)
        .map(ContactPerson::getName)
        .map(String::toUpperCase);


    return dto;
  }
}

class DeliveryDto {
  public Optional<String> recipientPerson; // tolerabil Optional<> pe DTO, dar nu pe obiecte de domain
}

class Parcel {
  private Delivery delivery; // NULL until a delivery is scheduled

  public Optional<Delivery> getDelivery() {
    return Optional.ofNullable(delivery);
  }

  public void setDelivery(Delivery delivery) {
    this.delivery = delivery;
  }
}


class Delivery {
//  @NotNull
  private Address address; // NOT NULL IN DB

  public Delivery(Address address) {
    this.address = Objects.requireNonNull(address);
  }

  public void setAddress(Address address) {
    this.address = Objects.requireNonNull(address); // TODO null safe
  }

  public Address getAddress() {
    return address;
  }
}

@Getter
class Address {
  private String streetName;
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
    this.name = Objects.requireNonNull(name);
  }

  public String getName() {
    return name;
  }
}
