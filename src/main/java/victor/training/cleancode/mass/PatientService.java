package victor.training.cleancode.mass;

public class PatientService {

  public void createPatient() {
    internalMethod();
  }

  public void updatePatient() {
    internalMethod();
  }

  // Inspection: ⇧⇧ can be weaker
  public void internalMethod() {

  }
}
