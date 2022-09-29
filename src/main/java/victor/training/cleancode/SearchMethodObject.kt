package victor.training.cleancode

import org.apache.commons.lang.StringUtils
import org.springframework.orm.jpa.EntityManagerFactoryAccessor
import javax.persistence.EntityManager

class SearchMethodObject(
    val criteria:CustomerSearchCriteria,
    val em: EntityManager
) {
    var jpql = "SELECT c.id FROM Customer c WHERE 1=1 "
    val params: MutableMap<String, Any?> = HashMap()

    fun execute(): List<Long> {
        if (StringUtils.isNotBlank(criteria.name)) {
            jpql += " AND UPPER(c.name) LIKE '%' || UPPER(:name) || '%' "
            params["name"] = criteria.name
        }
        part2()

        val query = em.createQuery("$jpql ", Long::class.java)
        for (param in params.keys) {
            query.setParameter(param, params[param])
        }
        return query.resultList
    }

    private fun part2() {
        if (criteria.countryId != null) {
            jpql += " AND (c.residenceCountry.id = :countryId OR ..<5 lines of JPQL>..)"
            params["countryId"] = criteria.countryId
        }
        if (criteria.countryId != null) {
            jpql += " AND (c.residenceCountry.id = :countryId OR ..<5 lines of JPQL>..)"
            params["countryId"] = criteria.countryId
        }
    }
}