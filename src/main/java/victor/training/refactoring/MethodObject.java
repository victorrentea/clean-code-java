package victor.training.refactoring;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class MethodObject {
   private final CustomerSearchCriteria criteria;
   private final long storeId;
   private String jpql = "SELECT c.id FROM Customer c WHERE 1=1 ";
   private Map<String, Object> params = new HashMap<>();
   private long storedId2 = 1;

   public MethodObject(CustomerSearchCriteria criteria, long storeId) {
      this.criteria = criteria;
      this.storeId = storeId;
   }

   public Map<String, Object> getParams() {
      return params;
   }

   public String getJpql() {
      return jpql;
   }

   public void execute() {
      addNameCriteria();
      addCountryCriteria();
   }

   private void addNameCriteria() {
      if (StringUtils.isNotBlank(criteria.name)) {
         jpql += " AND UPPER(c.name) LIKE '%' || UPPER(:name) || '%' ";
         params.put("name", criteria.name);
      }
   }

   private void addCountryCriteria() {
      if (criteria.countryId != null) {
         jpql += " AND (c.residenceCountry.id = :countryId OR ..<5 lines of JPQL>..)";
         params.put("countryId", criteria.countryId);
         System.out.println(storeId + storedId2);
      }
   }
}
