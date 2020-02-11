package victor.training.refactoring;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ExtractMethodObject {
    private final EntityManager em;

    public List<Long> search(CustomerSearchCriteria criteria) {
        CustomerQueryBuilder customerQueryBuilder =
                new CustomerQueryBuilder(criteria).build();

        String jpql = customerQueryBuilder.getJpql();
        Map<String, Object> params = customerQueryBuilder.getParams();

        TypedQuery<Long> query = em.createQuery(jpql + " ", Long.class);
        for (String param : params.keySet()) {
            query.setParameter(param, params.get(param));
        }
        return query.getResultList();
    }

    private class CustomerQueryBuilder {
        private final CustomerSearchCriteria criteria;
        private String jpql;
        @Getter
        private Map<String, Object> params = new HashMap<>();

        public String getJpql() {
            if (jpql ==null) throw  new IllegalStateException();
            return jpql;
        }

        public CustomerQueryBuilder(CustomerSearchCriteria criteria) {
            this.criteria = criteria;
        }

        public CustomerQueryBuilder build() {
            jpql = "SELECT c.id FROM Customer c WHERE 1=1 ";
            addNameCriterion();
            addCountryCriterion();
            return this;
        }

        private void addCountryCriterion() {
            if (criteria.countryId != null) {
                jpql += " AND (c.residenceCountry.id = :countryId OR ..<5 lines of JPQL>..)";
                params.put("countryId", criteria.countryId);
            }
        }

        private void addNameCriterion() {
            if (StringUtils.isNotBlank(criteria.name)) {
                jpql += "   AND UPPER(c.name) LIKE '%' || UPPER(:name) || '%' ";
                params.put("name", criteria.name);
            }
        }
    }
}

class CustomerSearchCriteria {

    public String name;
    public Long countryId;
}
