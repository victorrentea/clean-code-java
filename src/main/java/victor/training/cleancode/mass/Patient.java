package victor.training.cleancode.mass;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Patient implements Data {
  private String id;
  private String firstName;
  private String lastName;
  private String phoneNumber;
  private String email;
  @OldAnnotation
  private List<Consultation> consultations;

  public Patient() {
    consultations = new ArrayList<Consultation>();
    email = null;
  }


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPhonenumber() {
    return phoneNumber;
  }

  public void setPhonenumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<Consultation> getConsultations() {
    return consultations;
  }

  public void setConsultations(List<Consultation> consultations) {
    this.consultations = consultations;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Patient patient = (Patient) o;
    return Objects.equals(id, patient.id) && Objects.equals(firstName, patient.firstName) && Objects.equals(lastName, patient.lastName) && Objects.equals(phoneNumber, patient.phoneNumber) && Objects.equals(email, patient.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, phoneNumber, email);
  }

  @Override
  public String toString() {
    return "Patient{" +
           "id='" + id + '\'' +
           ", firstName='" + firstName + '\'' +
           ", lastName='" + lastName + '\'' +
           '}';
  }

  @Override
  public void fromXml(Document pDocument) {

  }

  @Override
  public Document toXml() {
    return null;
  }
}
