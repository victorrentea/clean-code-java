package victor.training.refactoring;

import org.apache.commons.lang.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CustomerRepo2 {
   private final EntityManager em;

   public CustomerRepo2(EntityManager em) {
      this.em = em;
   }

   public List<Long> search(CustomerSearchCriteria criteria) {
      return new CustomerSearchQuery(em, criteria).search();
   }
}

// method object
class CustomerSearchQuery {
   private final EntityManager em;
   private final CustomerSearchCriteria criteria;
   private String jpql = "SELECT c.id FROM Customer c WHERE 1=1 ";
   private Map<String, Object> params = new HashMap<>();

   public CustomerSearchQuery(EntityManager em, CustomerSearchCriteria criteria) {
      this.em = em;
      this.criteria = criteria;
   }

   public List<Long> search() {
      addNameCriteria();
      addCountryCriteria();

      TypedQuery<Long> query = em.createQuery(jpql + " ", Long.class);
      for (String param : params.keySet()) {
         query.setParameter(param, params.get(param));
      }
      return query.getResultList();
   }
   private void addCountryCriteria() {
      if (criteria.countryId != null) {
         jpql += " AND (c.residenceCountry.id = :countryId OR ..<5 lines of JPQL>..)";
         params.put("countryId", criteria.countryId);
      }
   }
   private void addNameCriteria() {
      if (StringUtils.isNotBlank(criteria.name)) {
         jpql += " AND UPPER(c.name) LIKE '%' || UPPER(:name) || '%' ";
         params.put("name", criteria.name);
      }
   }
}

class CustomerSearchCriteria {

   public String name;
   public Long countryId;
}
