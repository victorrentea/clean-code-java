package victor.training.cleancode.java;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GuardsTest {

  private final Guards guards = new Guards();

  // generated via a 10 minutes chat with GitHub Copilot
  @Test
  void throwsExceptionForBonusPackageValueLessThan10() {
    Marine marine = new Marine(false, false, 10, Collections.emptyList());
    BonusPackage bonusPackage = new BonusPackage(9);

    assertThatThrownBy(() -> guards.getPayAmount(marine, bonusPackage))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Not applicable!");
  }

  @Test
  void throwsExceptionForInvalidBonusPackageValueTooLarge() {
    Marine marine = new Marine(false, false, 10, Collections.emptyList());
    BonusPackage bonusPackage = new BonusPackage(101);

    assertThatThrownBy(() -> guards.getPayAmount(marine, bonusPackage))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Not applicable!");
  }

  @Test
  void throwsExceptionForNullMarine() {
    Marine marine = null;
    BonusPackage bonusPackage = new BonusPackage(50);

    assertThatThrownBy(() -> guards.getPayAmount(marine, bonusPackage))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Not applicable!");
  }

  @Test
  void throwsExceptionForInvalidBonusPackageValue() {
    Marine marine = new Marine(false, false, 10, Collections.emptyList());
    BonusPackage bonusPackage = new BonusPackage(200);

    assertThatThrownBy(() -> guards.getPayAmount(marine, bonusPackage));
  }

  @Test
  void calculatesPayAmountForActiveMarine() {
    Marine marine = new Marine(false, false, 10, Collections.emptyList());
    BonusPackage bonusPackage = new BonusPackage(50);

    int payAmount = guards.getPayAmount(marine, bonusPackage);

    assertThat(payAmount).isEqualTo(1050);
  }


  @Test
  void calculatesPayAmountForMarineWithThreeAwards() {
    Award award = new Award();
    Marine marine = new Marine(false, false, 10, List.of(award, award, award));
    BonusPackage bonusPackage = new BonusPackage(50);

    int payAmount = guards.getPayAmount(marine, bonusPackage);

    assertThat(payAmount).isEqualTo(4050);
  }

  @Test
  void calculatesPayAmountForRetiredMarine() {
    Marine marine = new Marine(false, true, 10, Collections.emptyList());
    BonusPackage bonusPackage = new BonusPackage(50);

    int payAmount = guards.getPayAmount(marine, bonusPackage);

    assertThat(payAmount).isEqualTo(2);
  }

  @Test
  void calculatesPayAmountForMarineWithAwards() {
    Award award = new Award();
    Marine marine = new Marine(false, false, 10, List.of(award));
    BonusPackage bonusPackage = new BonusPackage(50);

    int payAmount = guards.getPayAmount(marine, bonusPackage);

    assertThat(payAmount).isEqualTo(2050);
  }

  @Test
  void calculatesPayAmountForDeadMarine() {
    Marine marine = new Marine(true, false, 10, Collections.emptyList());
    BonusPackage bonusPackage = new BonusPackage(50);

    int payAmount = guards.getPayAmount(marine, bonusPackage);

    assertThat(payAmount).isEqualTo(Guards.DEAD_PAY_AMOUNT);
  }

  @Test
  void throwsExceptionForMarineWithNullYearsService() {
    Marine marine = new Marine(false, false, null, Collections.emptyList());
    BonusPackage bonusPackage = new BonusPackage(50);

    assertThatThrownBy(() -> guards.getPayAmount(marine, bonusPackage))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Any marine should have the years of service set");
  }

  @Test
  void throwsExceptionForInvalidBonusPackage() {
    Marine marine = new Marine(false, false, 10, Collections.emptyList());
    BonusPackage bonusPackage = new BonusPackage(200);

    assertThatThrownBy(() -> guards.getPayAmount(marine, bonusPackage))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Not applicable!");
  }
}