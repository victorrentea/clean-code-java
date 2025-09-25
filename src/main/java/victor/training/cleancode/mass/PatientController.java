package victor.training.cleancode.mass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {
  @Autowired
  private PatientService patientService;

  @GetMapping
  public String update() {
    patientService.createPatient();
    return "ok✅";
  }
}
