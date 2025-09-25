package victor.training.cleancode.mass;

import org.w3c.dom.Document;

public interface Data {
  default void fromXml(Document pDocument) {
  }
  default Document toXml() {
    return null;
  }
}
