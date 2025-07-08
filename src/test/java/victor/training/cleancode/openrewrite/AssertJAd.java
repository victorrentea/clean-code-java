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
import static org.junit.jupiter.api.Assertions.*;


@Disabled("enable to compare 💖AssertJ and 🤢JUnit Assertions")
public class AssertJAd { // from org.assertj:assertj-core, or via spring-boot-starter-test

  @Nested
  @TestMethodOrder(MethodName.class)
  public class Strings {
    String string = "abcdef";

    @Test
    public void startsWith_JUnit() {
      assertTrue(string.startsWith("bcd")); // see the failure message 🤢
    }

    @Test
    public void startsWith_AssertJ() {
      assertThat(string).startsWith("bcd"); // see the failure message
    }

    @Test
    public void ignoreCase_JUnit() {
      assertEquals("ABCDE", string.toUpperCase()); // looses the original case
    }

    @Test
    public void ignoreCase_AssertJ() {
      assertThat(string).isEqualToIgnoringCase("AbCdE");
    }

    @Test
    public void regex_JUnit() {
      assertTrue(string.matches(".*bd.*"));
    }

    @Test
    public void regex_AssertJ() {
      assertThat(string).matches(".*bd.*");
    }
  }

  @Nested
  @TestMethodOrder(MethodName.class)
  public class CollectionsPrimitives {
    List<Integer> aList = List.of(100, 200, 300, 300);

    @Test
    public void size1_JUnit() {
      assertEquals(1, aList.size());
    }

    @Test
    public void size1_AssertJ() {
      assertThat(aList).hasSize(1);
    }

    @Test
    public void onceInAnyOrder_JUnit() {
      assertTrue(aList.containsAll(List.of(100, 200, 700)));
      assertEquals(3, aList.size());
    }

    @Test
    public void onceInAnyOrder_AssertJ() {
      assertThat(aList).containsExactlyInAnyOrder(100, 200, 300);
    }

    @Test
    public void contains_JUnit() {
      assertTrue(aList.containsAll(List.of(100, 200, 400)));
      assertFalse(aList.contains(500));
    }

    @Test
    public void contains_AssertJ() {
      assertThat(aList)
          .contains(100, 200, 400)
          .doesNotContain(500);
    }

  }


  @Nested
  @TestMethodOrder(MethodName.class)
  public class Time {
    private final LocalDateTime oneMinAgo = now().minusMinutes(1);

    @Test
    public void deltaTime_JUnit() {
      assertTrue(oneMinAgo.isAfter(now().minusSeconds(1)));
    }

    @Test
    public void deltaTime_AssertJ() {
      assertThat(oneMinAgo).isCloseTo(now(), byLessThan(1, SECONDS));
    }
  }


}

