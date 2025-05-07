package victor.training.cleancode.fp.nonnull;

public class NullSafetyViaAnnotations {
  String field;

  public String method(String param) {
    return null;
  }

  public void caller() {
    System.out.println(method(null));
  }
}
