package victor.training.cleancode.fp;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jooq.lambda.Unchecked;

@RequiredArgsConstructor
public class FileExportService {
  private final FileExportService_Loan fileExporterService;
  private final UserExportWriter userExportWriter;
  private final OrderExportWriter orderExportWriter;

  @SneakyThrows
  public void exportOrders() {
    fileExporterService.exportFile("orders",
            Unchecked.consumer(orderExportWriter::writeOrders));
  }

  @SneakyThrows
  public void exportUsers() {
    fileExporterService.exportFile("users",
            Unchecked.consumer(userExportWriter::writeUsers));
  }
}
