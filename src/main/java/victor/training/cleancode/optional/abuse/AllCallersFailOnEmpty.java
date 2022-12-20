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

  public void flow1(long tenantId) {
    Tenant tenant = tenantRepo.findById(tenantId).get();
    System.out.println("Stuff1 with tenant: " + tenant);
  }

  public void flow2(long tenantId) {
    Tenant tenant = tenantRepo.findById(tenantId).get(); // done in 30 more places in code,
    System.out.println("Stuff2 with tenant: " + tenant);
  }
}
