package victor.training.cleancode.fp.nonnull;

import javax.annotation.Nullable;

public class NullSafetyViaAnnotations {
  String field;

  @Nullable
  public String method(@Nullable String param) {
    return null;
  }

  public void caller() {
    System.out.println(method(null));
  }
}
