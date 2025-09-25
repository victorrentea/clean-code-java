package victor.training.cleancode.mass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService extends BaseService<PatientDAO> {
  private static final Logger LOG = LoggerFactory.getLogger(PatientService.class);

  @Autowired
  public void inject(PatientDAO dao) {
    setDao(dao);
  }

  public void createPatient() {
    internalMethod("41");
  }

  public void updatePatient(Patient patient, User user) {
    internalMethod("42");
    LOG.debug("Message {}", patient);
    getDao().update(patient, user);
    if (Long.valueOf(1L) == 42) {
      System.out.println();
    }
    if (Long.valueOf("1") == 42) {
      System.out.println("Answer to life");
    }
  }

  // Inspection: ⇧⇧ can be weaker
  private void internalMethod(String id) {
    if (Long.parseLong(id) == 42) {
      System.out.println("Answer to life");
    }
  }
}
