package victor.training.cleancode.fp;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jooq.lambda.Unchecked;
import victor.training.cleancode.fp.support.OrderRepo;

@RequiredArgsConstructor
public class FileExportService {
  private final FileExportService_Loan fileExporterService;
  private final OrderRepo orderRepo;

  @SneakyThrows
  public void exportOrders() {
    fileExporterService.exportOrders("orders.csv", Unchecked.consumer(writer -> {
      writer.write("order_id;date\n");
      orderRepo.findByActiveTrue()
              .map(o -> o.getId() + ";" + o.getCreationDate() + "\n")
              .forEach(Unchecked.consumer(writer::write));

    }));
  }

  @SneakyThrows
  public void exportUsers() {
    // TODO implement the export of users using *the same workflow* as for orders
  }
}
