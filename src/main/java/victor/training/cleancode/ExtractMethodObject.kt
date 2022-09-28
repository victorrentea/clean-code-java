package victor.training.cleancode

import lombok.RequiredArgsConstructor
import org.apache.commons.lang.StringUtils
import javax.persistence.EntityManager

@RequiredArgsConstructor
class ExtractMethodObject {
    private val em: EntityManager? = null
    fun search(criteria: CustomerSearchCriteria): List<Long> {
        var jpql = "SELECT c.id FROM Customer c WHERE 1=1 "
        val params: MutableMap<String, Any?> = HashMap()

        if (StringUtils.isNotBlank(criteria.name)) {
            jpql += " AND UPPER(c.name) LIKE '%' || UPPER(:name) || '%' "
            params["name"] = criteria.name
        }
        if (criteria.countryId != null) {
            jpql += " AND (c.residenceCountry.id = :countryId OR ..<5 lines of JPQL>..)"
            params["countryId"] = criteria.countryId
        }

        val query = em!!.createQuery("$jpql ", Long::class.java)
        for (param in params.keys) {
            query.setParameter(param, params[param])
        }
        return query.resultList
    }
}

class CustomerSearchCriteria {
    var name: String? = null
    var countryId: Long? = null
}