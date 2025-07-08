package victor.training.cleancode.openrewrite;


import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftlyExtension;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.MethodName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static java.time.LocalDateTime.now;
import static java.time.temporal.ChronoUnit.SECONDS;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.*;


@Disabled("enable to compare 💖AssertJ and 🤢JUnit Assertions")
public class AssertJAd { // from org.assertj:assertj-core, or via spring-boot-starter-test

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
  class CollectionsElements {
    record Character(String name, int age, Race race) {
    }

    record Race(String name) {
    }

    private final List<Character> fellowship = List.of(
        new Character("Frodo", 20, new Race("Hobbit")),
        new Character("Sam", 18, new Race("Hobbit")),
        new Character("Legolas", 1000, new Race("Elf")),
        new Character("Boromir", 37, new Race("Man")),
        new Character("Gandalf the Gray", 120, new Race("Man")),
        new Character("Aragorn", 39, new Race("Man")),
        new Character("Gimli", 40, new Race("Dwarf"))
    );

    @Test
    void oneAttribute_JUnit() {
      // preprocess the collection before the assertion:
      Set<String> races = fellowship.stream()
          .map(Character::race)
          .map(Race::name)
          .collect(toSet());

      assertThat(races).isEqualTo(Set.of("Man", "Dwarf", "Elf", "Hobbit", "Orc"));
    }

    @Test
    void oneAttribute_AssertJ() {
      assertThat(fellowship)
          .map(Character::race)
          .map(Race::name)
          .containsOnly("Man", "Dwarf", "Elf", "Hobbit", "Orc");
    }

    @Test
    void multipleAttributes_JUnit() {
      assertThat(fellowship.stream().anyMatch(c ->
          c.name().equals("Frodo") &&
          c.age() == 20 &&
          c.race().name().equals("Hobbit"))).isTrue();
      assertThat(fellowship.stream().anyMatch(c ->
          c.name().equals("Aragorn") &&
          c.age() == 39 &&
          c.race().name().equals("Man"))).isTrue();
      assertThat(fellowship.stream().anyMatch(c ->
          c.name().equals("Legolas") &&
          c.age() == 100 && // ❌
//          c.age() == 1000 && // ✅
          c.race().name().equals("Elf"))).isTrue();
    }

    @Test
    void multipleAttributes_AssertJ() {
      assertThat(fellowship)
          // .extracting("name", "age", "race.name") // alternative
          .extracting(Character::name, Character::age, c -> c.race().name())
          .contains(
              tuple("Frodo", 20, "Hobbit"),
              tuple("Legolas", 100, "Elf"), // ❌
//              tuple("Legolas", 1000, "Elf"), // ✅
              tuple("Aragorn", 39, "Man")
          );
    }
  }

  @Nested
  @TestMethodOrder(MethodName.class)
  class Strings {
    private final String string = "abcdef";

    @Test
    void startsWith_JUnit() {
      assertThat(string).startsWith("bcd"); // see the failure message
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


  record Villa(int guests, String kitchen, String library) {
  }

  static Villa testedCode() {
    return new Villa(6, "dirty", "clean");
  }

  @Nested
  @TestMethodOrder(MethodName.class)
  public class CustomAssertions {
    @Test
    void extendingAssertJ() {
      Villa villa = testedCode();

      assertThat(villa)
          .hasGuests(7) // "extension methods" (java style👴🏻)
          .hasKitchen("clean")
          .hasLibrary("clean");
    }

    public static VillaAssert assertThat(Villa actual) {
      return new VillaAssert(actual);
    }

    static class VillaAssert extends AbstractAssert<VillaAssert, Villa> {
      protected VillaAssert(Villa actual) {
        super(actual, VillaAssert.class);
      }

      public VillaAssert hasGuests(int guests) {
        Assertions.assertThat(actual.guests()).as("Living Guests")
            .isEqualTo(guests);
        return this;
      }

      public VillaAssert hasKitchen(String kitchen) {
        Assertions.assertThat(actual.kitchen()).as("Kitchen")
            .isEqualTo(kitchen);
        return this;
      }

      public VillaAssert hasLibrary(String library) {
        Assertions.assertThat(actual.library()).as("Library")
            .isEqualTo(library);
        return this;
      }
    }

  }

  @Nested
  class SoftAssert {

    interface EventSender {
      void send(String event);

    }

    EventSender eventSender = Mockito.mock(EventSender.class);

    @Test
    void failsOnFirst_BAD() {
      Villa villa = testedCode();

      assertThat(villa.guests()).as("Living Guests").isEqualTo(7);
      assertThat(villa.kitchen()).as("Kitchen").isEqualTo("clean");
      assertThat(villa.library()).as("Library").isEqualTo("clean");
      Mockito.verify(eventSender).send("mansion-cleaned");
    }

    @Test
    void trySoftly() {
      Villa villa = testedCode();

      try (var softly = new AutoCloseableSoftAssertions()) {
        softly.assertThat(villa.guests()).as("Living Guests").isEqualTo(7);
        softly.assertThat(villa.kitchen()).as("Kitchen").isEqualTo("clean");
        softly.assertThat(villa.library()).as("Library").isEqualTo("clean");
        softly.assertThatCode(() -> Mockito.verify(eventSender).send("mansion-cleaned"))
            .as("event published").doesNotThrowAnyException();
      }
    }

    @Nested
    @ExtendWith(SoftlyExtension.class)
    class WithExtension {
      @InjectSoftAssertions
      SoftAssertions soft;

      @Test
      void usingExtension() {
        Villa villa = testedCode();

        soft.assertThat(villa.guests()).as("Living Guests").isEqualTo(7);
        soft.assertThat(villa.kitchen()).as("Kitchen").isEqualTo("clean");
        soft.assertThat(villa.library()).as("Library").isEqualTo("clean");
        soft.assertThatCode(() -> Mockito.verify(eventSender).send("mansion-cleaned"))
            .as("event published").doesNotThrowAnyException();
      }
    }
  }
}

