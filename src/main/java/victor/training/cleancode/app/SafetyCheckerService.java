package victor.training.cleancode.app;

import jakarta.validation.constraints.NotNull;

public interface SafetyCheckerService {
  @SuppressWarnings("DataFlowIssue")
  boolean isSafe(@NotNull String barcode);
}

//