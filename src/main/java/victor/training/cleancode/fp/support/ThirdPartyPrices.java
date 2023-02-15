package victor.training.cleancode.fp.support;

import java.util.List;
import java.util.Map;

public interface ThirdPartyPrices {
   double fetchPrice(Long id);

   Map<Long, Double> fetchAllPrices(List<Long> unknownProductIds);
}
