package victor.training.cleancode.mass;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Patient implements Data {
  private String id;
  private String firstName;
  private String lastName;
  private String phoneNumber;
  private String email;
  private Long processCenterId;
  @OldAnnotation
  private List<Consultation> consultations = new ArrayList<>();
  private List<Consultation> list2;


  @Override
  public String toString() {
    return "Patient{" +
           "id='" + id + '\'' +
           ", firstName='" + firstName + '\'' +
           ", lastName='" + lastName + '\'' +
           '}';
  }

}
