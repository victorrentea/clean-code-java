package victor.training.cleancode.mass;

import org.springframework.stereotype.Repository;

@Repository
public class PatientDAO implements DAO {
  public void update(Patient patient, User user) {
    user = UserHolder.getCurrentUserOr(user); // HOW can i be sure that coming in this method the thread has previously set a thread local
    System.out.println("UPDATE INTO .. LAST_MODIFIED_BY=" + user.getUsername());
  }

  public void create(Patient patient, User user) {
    user = UserHolder.getCurrentUser();
    System.out.println("INSERT INTO PATIENT " +
                       "VALUES (CREATED_BY=?...)");
    System.out.println("Set param: " + user.getUsername());
    System.out.println("Set param: " + patient.getId());
    System.out.println("Set param: " + patient.getProcessCenterId());
    System.out.println("Set param: " + patient.getEmail());
    System.out.println("Set param: " + patient.getFirstName());
    System.out.println("Set param: " + patient.getLastName());
    System.out.println("Set param: " + patient.getConsultations());
    System.out.println("Set param: " + patient.getPhoneNumber());
  }
}
