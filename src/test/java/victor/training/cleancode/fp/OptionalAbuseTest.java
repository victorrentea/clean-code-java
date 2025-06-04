package victor.training.cleancode.fp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import victor.training.cleancode.fp.OptionalAbuse.Entity;

import static org.assertj.core.api.Assertions.assertThat;
import static victor.training.cleancode.fp.OptionalAbuse.Dto;

@ExtendWith(MockitoExtension.class)
class OptionalAbuseTest {

  private final OptionalAbuse optionalAbuse = new OptionalAbuse();

  @Test
  void setsRecipientEmailWhenDtoHasValidRecipientPerson() {
    Dto dto = new Dto(" JohnDoe ", 30);

    Entity result = optionalAbuse.trappedOptional(dto);

    assertThat(result.getRecipient()).isEqualTo("johndoe@example.com");
  }

  @Test
  void setsAnonymousEmailWhenRecipientPersonIsBlank() {
    Dto dto = new Dto("   ", 30);

    Entity result = optionalAbuse.trappedOptional(dto);

    assertThat(result.getRecipient()).isEqualTo("anonymous@example.com");
  }

  @Test
  void setsAnonymousEmailWhenRecipientPersonIsNull() {
    Dto dto = new Dto(null, 30);

    Entity result = optionalAbuse.trappedOptional(dto);

    assertThat(result.getRecipient()).isEqualTo("anonymous@example.com");
  }

}