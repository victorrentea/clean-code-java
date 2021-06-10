package victor.training.samples.two;

import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Set;

public class ExAnteCostOverviewReportRequest {
   public List<ExAnteCostChapterDefinition> getChapterSelection() {
      return null;
   }

   public void setChapterSelection(List<ExAnteCostChapterDefinition> list) {

   }

   public String getNdg() {
      return null;
   }

   public String getIsin() {
      return null;
   }

   public boolean isCostOverview() {
      return getChapterSelection().contains(ExAnteCostChapterDefinition.COST_OVERVIEW);
   }
}
