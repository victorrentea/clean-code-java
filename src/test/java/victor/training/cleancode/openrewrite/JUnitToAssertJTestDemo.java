package victor.training.cleancode.openrewrite;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

class JUnitToAssertJTestDemo {
  @Test
  void test() {
    assertThat(testedCode()).contains("es");
    assertThat(testedCode()).startsWith("re");
  }

  private String testedCode() {
    return "result";
  }

  @Test
  void exceptions() {
    assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> throwingCode());
  }

  private void throwingCode() {
    throw new IllegalArgumentException("Boom");
  }
  // 1) run open-rewrite.yaml in IntelliJ, or
  // 2) mvn -U org.openrewrite.maven:rewrite-maven-plugin:run -Drewrite.recipeArtifactCoordinates=org.openrewrite.recipe:rewrite-testing-frameworks:RELEASE -Drewrite.activeRecipes=org.openrewrite.java.testing.assertj.JUnitAssertEqualsToAssertThat -Drewrite.exportDatatables=true

}

