package victor.training.cleancode.mass;

import org.w3c.dom.Document;

import java.time.LocalDateTime;

public class Consultation implements Data {
  private Patient patient;
  @OldAnnotation(
      name = "Why \n??"
  )
  private String notes;
  private LocalDateTime dateTime;
  private Medic medic;

  @Override
  public void fromXml(Document pDocument) {

  }

  @Override
  public Document toXml() {
    return null;
  }
}
