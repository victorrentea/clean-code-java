package victor.training.cleancode.mass;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Medic implements Data {
  private String name;
  @Getter
  @Setter
  private List<Consultation> consultations = new ArrayList<>();
  @Getter
  @Setter
  private List<Consultation> list2;
}
