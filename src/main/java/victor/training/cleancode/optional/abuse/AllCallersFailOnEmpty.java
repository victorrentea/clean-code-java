package victor.training.cleancode.optional.abuse;


import java.lang.reflect.ParameterizedType;
import java.util.NoSuchElementException;
import java.util.Optional;

public class AllCallersFailOnEmpty {
  //@Id
  // Spring pretend
  interface JpaRepository<T,PK> {
    Optional<T> findById(PK id);
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

  //@Entity // pretend
  private static class Tenant {
    Long id;
  }
//  private interface TenantRepo extends BaseRepo<Tenant, Long> {
  private interface TenantRepo extends JpaRepository<Tenant, Long> {
  }


  private TenantRepo tenantRepo;

  public void flow1(long tenantId) {
    Tenant tenant = tenantRepo.findById(tenantId).get(); // .get() throws if Optional is empty
    System.out.println("Stuff1 with tenant: " + tenant);
  }

  public void flow2(long tenantId) {
    Tenant tenant = tenantRepo.findById(tenantId).get(); // + 30 more places in a typical project
    System.out.println("Stuff2 with tenant: " + tenant);
  }
}
