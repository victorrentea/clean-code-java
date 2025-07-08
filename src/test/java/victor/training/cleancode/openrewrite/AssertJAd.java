package victor.training.cleancode.openrewrite;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.MethodName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.now;
import static java.time.temporal.ChronoUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.byLessThan;


@Disabled("enable to compare 💖AssertJ and 🤢JUnit Assertions")
public class AssertJAd { // from org.assertj:assertj-core, or via spring-boot-starter-test

  @Nested
  @TestMethodOrder(MethodName.class)
  class Strings {
    String string = "abcdef";

    @Test
    void startsWith_JUnit() {
      assertThat(string).startsWith("bcd"); // see the failure message 🤢
    }

    @Test
    void startsWith_AssertJ() {
      assertThat(string).startsWith("bcd"); // see the failure message
    }

    @Test
    void ignoreCase_JUnit() {
      assertThat(string.toUpperCase()).isEqualTo("ABCDE"); // looses the original case
    }

    @Test
    void ignoreCase_AssertJ() {
      assertThat(string).isEqualToIgnoringCase("AbCdE");
    }

    @Test
    void regex_JUnit() {
      assertThat(string).matches(".*bd.*");
    }

    @Test
    void regex_AssertJ() {
      assertThat(string).matches(".*bd.*");
    }
  }

  @Nested
  @TestMethodOrder(MethodName.class)
  class CollectionsPrimitives {
    List<Integer> aList = List.of(100, 200, 300, 300);

    @Test
    void size1_JUnit() {
      assertThat(aList).hasSize(1);
    }

    @Test
    void size1_AssertJ() {
      assertThat(aList).hasSize(1);
    }

    @Test
    void onceInAnyOrder_JUnit() {
      assertThat(aList)
          .containsAll(List.of(100, 200, 700))
          .hasSize(3);
    }

    @Test
    void onceInAnyOrder_AssertJ() {
      assertThat(aList).containsExactlyInAnyOrder(100, 200, 300);
    }

    @Test
    void contains_JUnit() {
      assertThat(aList)
          .containsAll(List.of(100, 200, 400))
          .doesNotContain(500);
    }

    @Test
    void contains_AssertJ() {
      assertThat(aList)
          .contains(100, 200, 400)
          .doesNotContain(500);
    }

  }


  @Nested
  @TestMethodOrder(MethodName.class)
  class Time {
    private final LocalDateTime oneMinAgo = now().minusMinutes(1);

    @Test
    void deltaTime_JUnit() {
      assertThat(oneMinAgo.isAfter(now().minusSeconds(1))).isTrue();
    }

    @Test
    void deltaTime_AssertJ() {
      assertThat(oneMinAgo).isCloseTo(now(), byLessThan(1, SECONDS));
    }
  }


}

