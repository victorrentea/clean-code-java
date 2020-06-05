package victor.training.refactoring;

import org.apache.commons.lang.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtractMethodObject {
    private final EntityManager em;

    public ExtractMethodObject(EntityManager em) {
        this.em = em;
    }

    public List<Long> search(CustomerSearchCriteria criteria) {
        String jpql = "SELECT c.id FROM Customer c WHERE 1=1 ";
        Map<String, Object> params = new HashMap<>();

        if (StringUtils.isNotBlank(criteria.name)) {
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
