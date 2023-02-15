package victor.training.cleancode.optional.abuse;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.lang.reflect.ParameterizedType;
import java.util.NoSuchElementException;

public class AllCallersFailOnEmpty {
  @Entity
  private static class Tenant {
    @Id
    Long id;
    //    @Transient
    //    transient String IcanTooBeStupid;
  }

  private interface BaseRepo<T,PK> extends JpaRepository<T, PK> {
    @SuppressWarnings("unchecked")
    default T findOneById(PK id) {
      return findById(id).orElseThrow(() -> {
        Class<T> persistentClass = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return new NoSuchElementException(persistentClass.getSimpleName() + " with id " + id + " not found ");
      });
    }
  }

//  private interface TenantRepo extends BaseRepo<Tenant, Long> {
  private interface TenantRepo extends JpaRepository<Tenant, Long> {
  }


  private TenantRepo tenantRepo;

  public void flow1(long tenantId) {
    Tenant tenant = tenantRepo.findById(tenantId).get(); // throws if Optional is empty
    System.out.println("Stuff1 with tenant: " + tenant);
  }

  public void flow2(long tenantId) {
    Tenant tenant = tenantRepo.findById(tenantId).get(); // done in 30 more places in code,
    System.out.println("Stuff2 with tenant: " + tenant);
  }
}
