package victor.training.cleancode.mass;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PatientService extends BaseService<PatientDAO> {
  private static final Logger LOG = LoggerFactory.getLogger(PatientService.class);

  @PostConstruct
  public void init() {
    setDao(new PatientDAO());
  }

  public void createPatient() {
    internalMethod("41");
  }

  public void updatePatient(Patient patient, User user) {
    internalMethod("42");
    LOG.debug("Message " + patient);
    getDao().update(patient, user);
  }

  // Inspection: ⇧⇧ can be weaker
  public void internalMethod(String id) {
    if (new Long(id).longValue() == 42) {
      System.out.println("Answer to life");
    }
  }
}
