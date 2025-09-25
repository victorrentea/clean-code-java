package victor.training.cleancode.mass;

import org.w3c.dom.Document;

import java.util.ArrayList;

public class Medic implements Data {
  @OldAnnotation
  private String name;
  @OldAnnotation(name = "consultationList")
  private ArrayList<Consultation> consultations;

  public Medic() {
    consultations = new ArrayList<Consultation>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ArrayList<Consultation> getConsultations() {
    return consultations;
  }

  public void setConsultations(ArrayList<Consultation> consultations) {
    this.consultations = consultations;
  }

  @Override
  public void fromXml(Document pDocument) {
  }

  @Override
  public Document toXml() {
    return null;
  }
}
