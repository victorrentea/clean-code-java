package victor.training.cleancode.openrewrite;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JUnitTestDemo {
  @Test
  public void test() {
    assertEquals(1, notification());
  }

  private Integer notification() {
    return 1;
  }

  // mvn -U org.openrewrite.maven:rewrite-maven-plugin:run -Drewrite.recipeArtifactCoordinates=org.openrewrite.recipe:rewrite-testing-frameworks:RELEASE -Drewrite.activeRecipes=org.openrewrite.java.testing.assertj.JUnitAssertEqualsToAssertThat -Drewrite.exportDatatables=true

}

