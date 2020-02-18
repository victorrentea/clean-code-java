package victor.training.refactoring;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import victor.training.cleancode.pretend.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ExtractMethodObject {
    private final EntityManager em;
    private static final Logger log = LoggerFactory.getLogger(ExtractMethodObject.class);


    public List<Long> search(CustomerSearchCriteria criteria) {
        CustomerSearchQuery q = new CustomerSearchQuery(criteria);
        q.execute();


        TypedQuery<Long> query = em.createQuery(q.getJpql(), Long.class);

        log.debug("params: " + q.getParams());

        for (String param : q.getParams().keySet()) {
            query.setParameter(param, q.getParams().get(param));
        }
        return query.getResultList();
    }
}

class CustomerSearchQuery {
    private String jpql = "SELECT c.id FROM Customer c WHERE 1=1 ";
    private final Map<String, Object> params = new HashMap<>();
    private final CustomerSearchCriteria criteria;

    CustomerSearchQuery(CustomerSearchCriteria criteria) {
        this.criteria = criteria;
    }

    public void execute() {
        addNameCriterion();
        addCountryCritrion();
    }

    private void addNameCriterion() {
        if (StringUtils.isNotBlank(criteria.name)) {
            jpql += " AND UPPER(c.name) LIKE '%' || UPPER(:name) || '%' ";
            params.put("name", criteria.name);
        }
    }

    private void addCountryCritrion() {
        if (criteria.countryId != null) {
            jpql += " AND (c.residenceCountry.id = :countryId OR ..<5 lines of JPQL>..)";
            params.put("countryId", criteria.countryId);
        }
    }

    public String getJpql() {
        return jpql;
    }

    public Map<String, Object> getParams() {
        return params;
    }
}







class CustomerSearchCriteria {

    public String name;
    public Long countryId;
}
