package victor.training.refactoring;

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
        MethodObject method = new MethodObject(criteria, 1);
        method.execute();

        String jpql = method.getJpql();
        Map<String, Object> params = method.getParams();

        TypedQuery<Long> query = em.createQuery(jpql + " ", Long.class);
        for (String param : params.keySet()) {
            query.setParameter(param, params.get(param));
        }
        List<Long> resultList = query.getResultList();
        if (true) {
        resultList = query.getResultList();

        }
        return resultList;
    }
}

class CustomerSearchCriteria {

    public String name;
    public Long countryId;
}
