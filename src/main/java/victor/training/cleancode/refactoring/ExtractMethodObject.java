package victor.training.cleancode.refactoring;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ExtractMethodObject {
  private final EntityManager em;

  public List<Long> search(CustomerSearchCriteria criteria) {
    String jpql = "SELECT c.id FROM Customer c WHERE 1=1 ";
    Map<String, Object> params = new HashMap<>();

    if (criteria.name != null) {
      jpql += " AND UPPER(c.name) LIKE '%' || UPPER(:name) || '%' ";
      params.put("name", criteria.name);
    }

    if (criteria.countryId != null) {
      jpql += " AND (c.residenceCountry.id = :countryId OR ..<5 lines of JPQL>..)";
      params.put("countryId", criteria.countryId);
    }

    TypedQuery<Long> query = em.createQuery(jpql + " ", Long.class);
    for (String param : params.keySet()) {
      query.setParameter(param, params.get(param));
    }
    return query.getResultList();
  }
}

class CustomerSearchCriteria {

  public String name;
  public Long countryId;
}
