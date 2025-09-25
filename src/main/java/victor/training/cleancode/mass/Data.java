package victor.training.cleancode.mass;

import org.w3c.dom.Document;

public interface Data {
  void fromXml(Document pDocument);
  Document toXml();
}
