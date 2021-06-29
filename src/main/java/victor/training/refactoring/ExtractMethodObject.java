package victor.training.refactoring;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class QueryComposer {
    private final CustomerSearchCriteria criteria;
    private String jpql = "SELECT c.id FROM Customer c WHERE 1=1 ";
    private Map<String, Object> params = new HashMap<>();

    QueryComposer(CustomerSearchCriteria criteria) {
        this.criteria = criteria;
    }

    public String getJpql() {
        return jpql;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public QueryComposer invoke() {

        if (StringUtils.isNotBlank(criteria.name)) {
            jpql += " AND UPPER(c.name) LIKE '%' || UPPER(:name) || '%' ";
            params.put("name", criteria.name);
        }

        if (criteria.countryId != null) {
            jpql += " AND (c.residenceCountry.id = :countryId OR ..<5 lines of JPQL>..)";
            params.put("countryId", criteria.countryId);
        }
        return this;
    }
}


@RequiredArgsConstructor
public class ExtractMethodObject {
    private final EntityManager em;

    public List<Long> search(CustomerSearchCriteria criteria) {
        QueryComposer queryComposer = new QueryComposer(criteria).invoke();
        String jpql = queryComposer.getJpql();
        Map<String, Object> params = queryComposer.getParams();

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
