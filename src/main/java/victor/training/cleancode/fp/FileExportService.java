package victor.training.cleancode.fp;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class FileExportService {
  private final FileExportService_Loan fileExporterService;

  @SneakyThrows
  public void exportOrders() {
    fileExporterService.exportOrders();
  }

  @SneakyThrows
  public void exportUsers() {
    // TODO implement the export of users using *the same workflow* as for orders
  }
}
