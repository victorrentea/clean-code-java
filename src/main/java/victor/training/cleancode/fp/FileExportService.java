package victor.training.cleancode.fp;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jooq.lambda.Unchecked;

@RequiredArgsConstructor
public class FileExportService {
  private final FileExportService_Loan fileExporterService;

  @SneakyThrows
  public void exportOrders() {
    fileExporterService.exportOrders("orders", Unchecked.consumer(fileExporterService::writeOrders));
  }

  @SneakyThrows
  public void exportUsers() {
    fileExporterService.exportOrders("orders", Unchecked.consumer(fileExporterService::writeUsers));
    // TODO implement the export of users using *the same workflow* as for orders
  }
}
