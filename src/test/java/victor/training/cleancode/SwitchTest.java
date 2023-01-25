package victor.training.cleancode;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import victor.training.cleancode.Movie.Category;
import victor.training.testing.tools.CaptureSystemOutput;
import victor.training.testing.tools.CaptureSystemOutput.OutputCapture;

import static org.assertj.core.api.Assertions.assertThat;

class SwitchTest {

  private Switch target = new Switch();

  @ParameterizedTest
  @CsvSource({
          "REGULAR,4,5",
          "NEW_RELEASE,3,6",
          "CHILDREN,100,5"})
  void computePrice(Movie.Category category, int days, int expectedPrice) {
    assertThat(target.computePrice(new Movie(category, "noop"), days)).isEqualTo(expectedPrice);
  }

  @Test
  @CaptureSystemOutput
  void processMovie_regular(OutputCapture outputCapture) {
    target.processMovie(new Movie(Category.REGULAR, "MMM"));
    assertThat(outputCapture.toString()).isEqualToNormalizingNewlines("Some repo calls\n" +
                                                                      "Some shared initial stuff\n" +
                                                                      "Process regular movie: Movie{category=REGULAR, title='MMM'}\n" +
                                                                      "More common code after\n");
  }
  @Test
  @CaptureSystemOutput
  void processMovie_new_release(OutputCapture outputCapture) {
    target.processMovie(new Movie(Category.NEW_RELEASE, "MMM"));
    assertThat(outputCapture.toString()).isEqualToNormalizingNewlines("Some repo calls\n" +
                                                                      "Some shared initial stuff\n" +
                                                                      "Process new release movie: Movie{category=NEW_RELEASE, title='MMM'}\n" +
                                                                      "More common code after\n");
  }
  @Test
  @CaptureSystemOutput
  void processMovie_children(OutputCapture outputCapture) {
    target.processMovie(new Movie(Category.CHILDREN, "MMM"));
    assertThat(outputCapture.toString()).isEqualToNormalizingNewlines("Some repo calls\n" +
                                                                      "Some shared initial stuff\n" +
                                                                      "Process children movie: Movie{category=CHILDREN, title='MMM'}\n" +
                                                                      "More common code after\n");
  }
}