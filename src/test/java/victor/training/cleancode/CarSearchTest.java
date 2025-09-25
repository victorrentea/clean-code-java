package victor.training.cleancode;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CarSearchTest {

  private final CarSearch searchEngine = new CarSearch();

  private final CarModel fordFocusMk2 = new CarModel("Ford", "Focus", 2012, 2016);

   // Ford Focus:     [2012 ---- 2016]
   // Search:              [2014 ---- 2018]
   // can't afford a 2021 car
   @Test
   void byYear_match() {
      CarSearchCriteria criteria = new CarSearchCriteria(2014, 2018, "Ford");

      List<CarModel> models = searchEngine.filterCarModels(criteria, List.of(fordFocusMk2));

      assertThat(models).hasSize(1);
   }
   @Test
   void byYear_no_match() {
      CarSearchCriteria criteria = new CarSearchCriteria(2017, 2018, "Ford");

      List<CarModel> models = searchEngine.filterCarModels(criteria, List.of(fordFocusMk2));

     assertThat(models).isEmpty();
   }
}