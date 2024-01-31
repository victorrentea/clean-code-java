package victor.training.cleancode.optional;

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

// there are 2 main ways to avoid NPEs:
//1> Monads: Opt[] Optional (java standard)
//2> annotations ?/nullability: @Nullable @NotNull (JSR 305)
//  -checked by IDE:(
//  -checked by compiler: Kotlin
class MyMapper {
  public DeliveryDto convert(Parcel parcel) {
    DeliveryDto dto = new DeliveryDto();
    dto.recipientPerson = parcel.getDelivery()
        .flatMap(d->d.getAddress().getContactPerson())
        .map(p->p.getName().toUpperCase());
    return dto;
  }
}

class DeliveryDto {
  public Optional<Object> recipientPerson;
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
  private Address address; // NOT NULL IN DB 🥜

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
    this.name = Objects.requireNonNull(name);
  }

  public String getName() {
    return name;
  }
}
