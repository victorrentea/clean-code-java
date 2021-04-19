//package victor.training.extendrepo;
//
//import lombok.Data;
//
//import javax.persistence.EntityManager;
//import java.util.HashMap;
//import java.util.Map;
//
//public class SomeBizLogic {
//   private MyRepo myRepo;
//
//   public void bizLogic() {
//      myRepo.saveAndCommit();
//   }
//}
//
//
//class MyRepo /*implements IMyRepo*/{ // prod code
//   private final  EntityManager em;
//
//   MyRepo(EntityManager em) {
//      this.em = em;
//   }
//
//   public void save2(String order) {
//      throw new IllegalArgumentException("Never works in test ");
//   }
//   public void saveAndCommit(String order) {
//      throw new IllegalArgumentException("Never works in test ");
//   }
//}
//
//class MyRepoHackedForTests extends MyRepo { // /src/test/java
//   private Map<Long, String> data = new HashMap<>();
//
//   MyRepoHackedForTests() {
//      super(null);
//   }
//
//   @Override
//   public void save2(String order) {
//
//   }
//}
//@Data
//class Order {
//   private Long id;
//
//}