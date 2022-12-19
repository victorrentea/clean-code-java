package victor.training.cleancode.optional.abuse;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import javax.persistence.Id;

public class AllCallersFailOnEmpty {
  @Entity
  private static class Tenant {
    @Id
    Long id;
  }
  private interface TenantRepo extends JpaRepository<Tenant, Long> {
  }

  private TenantRepo tenantRepo;

  public void allCallersFailOnEmpty(long tenantId) {
    Tenant tenant = tenantRepo.findById(tenantId).get(); // done in 30 places in code,
    System.out.println("Stuff with tenant: " + tenant);
  }
}
