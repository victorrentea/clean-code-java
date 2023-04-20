package victor.training.cleancode.optional.abuse;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.lang.reflect.ParameterizedType;
import java.util.NoSuchElementException;

public class AllCallersFailOnEmpty {
  @Entity
  private static class Tenant {
    @Id
    Long id;
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

  public void flow1(long tenantId) {
    // when I go to DB with a PK, if I don't find it, i ALWAYS fail throw
    //    Tenant tenant = tenantRepo.findById(tenantId).orElseThrow(); // .get() throws if Optional is empty
    Tenant tenant = tenantRepo.findOneById(tenantId);
    System.out.println("Stuff1 with tenant: " + tenant);
  }


  private TenantRepo tenantRepo;

  public void flow2(long tenantId) {
    Tenant tenant = tenantRepo.findOneById(tenantId);
    System.out.println("Stuff2 with tenant: " + tenant);
  }

//  private interface TenantRepo extends BaseRepo<Tenant, Long> {
private interface TenantRepo extends BaseRepo<Tenant, Long> {
}
}
