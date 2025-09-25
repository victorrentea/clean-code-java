package victor.training.cleancode.mass;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Job {
  private final PatientService patientService;

  public Job(PatientService patientService) {
    this.patientService = patientService;
  }

  @Scheduled(fixedRate = 10_000)
  public void every10sec() {
    patientService.updatePatient(new Patient(), new User("job"));
  }
}
