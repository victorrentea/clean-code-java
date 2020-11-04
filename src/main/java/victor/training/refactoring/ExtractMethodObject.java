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
    private final MyNewShinyService newShinyService;

    public List<Long> search(CustomerSearchCriteria criteria) {
        return new SearchMethod(criteria, em).search();
    }
    public void altaFunctie() {
        newShinyService.method();
    }

}
class MyNewShinyService {

    public void method() {

    }
}


class CustomerSearchCriteria {

    public String name;
    public Long countryId;
}
