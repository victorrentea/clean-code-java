package victor.training.cleancode

import lombok.RequiredArgsConstructor
import org.apache.commons.lang.StringUtils
import javax.persistence.EntityManager

@RequiredArgsConstructor
class ExtractMethodObject {
    private val em: EntityManager? = null
    fun search(criteria: CustomerSearchCriteria): List<Long> {
        return SearchMethodObject(criteria,em!!).execute()
    }
}

class CustomerSearchCriteria {
    var name: String? = null
    var countryId: Long? = null
}