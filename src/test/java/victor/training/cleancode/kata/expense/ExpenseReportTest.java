package victor.training.cleancode.kata.expense;

import org.junit.jupiter.api.Test;
import victor.training.cleancode.CaptureSystemOutput;
import victor.training.cleancode.CaptureSystemOutput.OutputCapture;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ExpenseReportTest {

  private ExpenseReport report = new ExpenseReport();

  @Test
  @CaptureSystemOutput
  void decent(OutputCapture outputCapture) {
    report.printReport(List.of(
        new Expense("DINNER", 100),
        new Expense("DINNER", 100),
        new Expense("BREAKFAST", 50),
        new Expense("CAR_RENTAL", 200)
    ));

    assertThat(outputCapture.toString()).isEqualToIgnoringWhitespace(
        """
            Expenses Report
            Dinner	100	\s
            Dinner	100	\s
            Breakfast	50	\s
            Car Rental	200	\s
            Meal expenses: 250
            Total expenses: 450
            """);
  }

  @Test
  @CaptureSystemOutput
  void bigBreakfast(OutputCapture outputCapture) {
    report.printReport(List.of(
        new Expense("BREAKFAST", 1200)
    ));

    assertThat(outputCapture.toString()).isEqualToIgnoringWhitespace(
        """
            Expenses Report
            Breakfast	1200	X
            Meal expenses: 1200
            Total expenses: 1200
            Meal expenses exceed limit
            """);
  }

  @Test
  @CaptureSystemOutput
  void bigDinner(OutputCapture outputCapture) {
    report.printReport(List.of(
        new Expense("DINNER", 5200)
    ));

    assertThat(outputCapture.toString()).isEqualToIgnoringWhitespace(
        """
            Expenses Report
            Dinner	5200	X
            Meal expenses: 5200
            Total expenses: 5200
            Meal expenses exceed limit
            """);
  }
}