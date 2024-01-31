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
//      findById(id).orElse(takeMoney()); // DANGEROUS. will call takeMoney() even if Optional is not empty
//      findById(id).orElseGet(()->takeMoney());
//      if (findById(id).isEmpty()) {
//        takeMoney();
//      }
      return findById(id).orElseThrow(() -> {
        Class<T> persistentClass = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return new NoSuchElementException(persistentClass.getSimpleName() + " with id " + id + " not found ");
      });
    }

//    T takeMoney();
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
