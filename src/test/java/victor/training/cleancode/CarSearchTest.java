package victor.training.cleancode;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CarSearchTest {
   CarSearch searchEngine = new CarSearch();

   CarModel fordFocusMk2 = new CarModel("Ford", "Focus", 2012, 2016);

   @Test
   void byYear_match() {
      CarSearchCriteria criteria = new CarSearchCriteria(2014, 2018, "Ford", null);

      List<CarModel> models = searchEngine.filterCarModels(criteria, List.of(fordFocusMk2));

      assertThat(models).hasSize(1);
   }
   @Test
   void byYear_no_match() {
      CarSearchCriteria criteria = new CarSearchCriteria(2017, 2018, "Ford", null);

      List<CarModel> models = searchEngine.filterCarModels(criteria, List.of(fordFocusMk2));

     assertThat(models).isEmpty();
   }
}