package victor.training.cleancode.openrewrite;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

class JUnitToAssertJTestDemo {
  @Test
  void test() {
    assertThat(testedCode()).contains("eS");
    assertThat(testedCode()).startsWith("re");
  }

  private String testedCode() {
    return notification(false);
  }


  @Test
  void exceptions() {
    assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> notification(true));
  }

  private String notification(boolean shouldThrow) {
    if (shouldThrow) {
      throw new IllegalArgumentException("Boom");
    }
    return "result";
  }
  // 1) run openrewrite-assertj.yaml in IntelliJ, or
  // 2) mvn -U org.openrewrite.maven:rewrite-maven-plugin:run -Drewrite.recipeArtifactCoordinates=org.openrewrite.recipe:rewrite-testing-frameworks:RELEASE -Drewrite.activeRecipes=org.openrewrite.java.testing.assertj.JUnitAssertEqualsToAssertThat -Drewrite.exportDatatables=true

}

