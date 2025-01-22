package victor.training.cleancode.java.kata.povalidator;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class POValidatorTest {
  POItem item = new POItem();

  @Test
  void validatePurchaseOrderItem_ItemValid_ReturnsTrue() {
    var validator = new POValidator();
    validator.setValidators(Collections.emptyList());

    var result = validator.validatePurchaseOrderItem(item);

    assertThat(result).isTrue();
    assertThat(item.getStatus()).isEqualTo(POItem.Status.NEW);
    assertThat(item.getMessages()).isEmpty();
  }

  @Test
  void validatePurchaseOrderItem_ItemInvalid_ReturnsFalseAndSetsStatus() {
    Validator failingValidator = mock(Validator.class);
    when(failingValidator.validate(any())).thenReturn(false);
    when(failingValidator.message(any())).thenReturn("Validation failed");
    var validator = new POValidator();
    validator.setValidators(List.of(failingValidator));

    var result = validator.validatePurchaseOrderItem(item);

    assertThat(result).isFalse();
    assertThat(item.getStatus()).isEqualTo(POItem.Status.ERROR);
    assertThat(item.getMessages()).hasSize(1);
    assertThat(item.getMessages().get(0).getText()).isEqualTo("Validation failed");
  }

  @Test
  void validatePurchaseOrderItem_whenItemFails_midway_through() {
    Validator failingValidator = mock(Validator.class);
    when(failingValidator.validate(item)).thenReturn(Boolean.FALSE);
    Validator uncalledValidator = mock(Validator.class);
    POValidator validator = new POValidator();
    validator.setValidators(List.of(failingValidator, uncalledValidator));

    assertThat(validator.validatePurchaseOrderItem(item)).isFalse();

    verify(uncalledValidator, never()).validate(any());
  }
}