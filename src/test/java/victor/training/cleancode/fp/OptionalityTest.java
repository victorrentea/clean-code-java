package victor.training.cleancode.fp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import victor.training.cleancode.fp.E4_Optionality.Entity;

import static org.assertj.core.api.Assertions.assertThat;
import static victor.training.cleancode.fp.E4_Optionality.Dto;

@ExtendWith(MockitoExtension.class)
public class OptionalityTest {

  private final E4_Optionality optionality = new E4_Optionality();

  @Test
  void setsRecipientEmailWhenDtoHasValidRecipientPerson() {
    Dto dto = new Dto(" JohnDoe ", 30);

    Entity result = optionality.trappedOptional(dto);

    assertThat(result.getRecipient()).isEqualTo("johndoe@example.com");
  }

  @Test
  void setsAnonymousEmailWhenRecipientPersonIsBlank() {
    Dto dto = new Dto("   ", 30);

    Entity result = optionality.trappedOptional(dto);

    assertThat(result.getRecipient()).isEqualTo("anonymous@example.com");
  }

  @Test
  void setsAnonymousEmailWhenRecipientPersonIsNull() {
    Dto dto = new Dto(null, 30);

    Entity result = optionality.trappedOptional(dto);

    assertThat(result.getRecipient()).isEqualTo("anonymous@example.com");
  }

}