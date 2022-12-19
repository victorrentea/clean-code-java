package victor.training.cleancode.optional.abuse;

import org.springframework.data.jpa.repository.JpaRepository;
import victor.training.cleancode.optional.abuse.repo.custom.CustomJpaRepository;

import javax.persistence.Entity;
import javax.persistence.Id;

public class AllCallersFailOnEmpty {
  @Entity
  private static class Tenant {
    @Id
    Long id;
  }
  private interface TenantRepo extends CustomJpaRepository<Tenant, Long> {
  }

  private TenantRepo tenantRepo;

  public void allCallersFailOnEmpty(long tenantId) {
    Tenant tenant = tenantRepo.getExactlyOne(tenantId); // done in 30 places in code,
    // 0 palces in care continuam executia
    System.out.println("Stuff with tenant: " + tenant);
  }
}
