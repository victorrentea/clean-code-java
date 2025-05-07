package victor.training.cleancode.fp;

import lombok.Data;

public class E4_OptionalObsession {
  public Entity trappedOptional(Dto dto) {
    Entity entity = new Entity();
    entity.setAge(dto.age());
    var personName = dto.recipientPerson();
    if (personName != null && !personName.isBlank()) {
      entity.setRecipient(personName.trim().toLowerCase() + "@example.com");
    } else {
      entity.setRecipient("anonymous@example.com");
    }
    return entity;
  }

  public record Dto(String recipientPerson, int age) {
  }

  @Data
  public static class Entity {
    private String recipient;
    private int age;
  }
}
