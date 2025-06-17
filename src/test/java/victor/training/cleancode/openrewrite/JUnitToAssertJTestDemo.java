package victor.training.cleancode.openrewrite;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JUnitToAssertJTestDemo {
  @Test
  void test() {
    assertTrue(testedCode().contains("eS"));
    assertTrue(testedCode().startsWith("re"));
  }

  private String testedCode() {
    return notification(false);
  }


  @Test
  void testExceptions() {
    assertThrows(IllegalArgumentException.class, () -> notification(true));
  }

  private String notification(boolean shouldThrow) {
    if (shouldThrow) throw new IllegalArgumentException("Boom");
    return "result";
  }
  // 1) run open-rewrite.yaml in IntelliJ, or
  // 2) mvn -U org.openrewrite.maven:rewrite-maven-plugin:run -Drewrite.recipeArtifactCoordinates=org.openrewrite.recipe:rewrite-testing-frameworks:RELEASE -Drewrite.activeRecipes=org.openrewrite.java.testing.assertj.JUnitAssertEqualsToAssertThat -Drewrite.exportDatatables=true

}

