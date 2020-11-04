package victor.training.refactoring;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class SearchMethod {
   private final CustomerSearchCriteria criteria;
   private final EntityManager em;

   private String jpql = "SELECT c.id FROM Customer c WHERE 1=1 ";
   private Map<String, Object> params = new HashMap<>();

   public List<Long> search() {
      addNameCriteria();
      addCountryCriteria();
      return runQuery();
   }

   private List<Long> runQuery() {
      TypedQuery<Long> query = em.createQuery(jpql + " ", Long.class);
      for (String param : params.keySet()) {
         query.setParameter(param, params.get(param));
      }
      return query.getResultList();
   }

   private void addCountryCriteria() {
//        if (params.containsKey("ceva")) {
//            throw new IllegalArgumentException();
//        }
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
