package victor.training.cleancode.fp;

import lombok.RequiredArgsConstructor;
import org.jooq.lambda.Unchecked;
import victor.training.cleancode.fp.support.OrderRepo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

@RequiredArgsConstructor
class FileExportService {
   private final OrderRepo orderRepo;

   public void exportOrders() throws IOException {
      File file = new File("target/orders.csv");
      System.out.println("Starting export into {} ... " + file.getAbsolutePath());
      long t0 = System.currentTimeMillis();
      try (Writer writer = new FileWriter(file)) {

         writer.write("order_id;date\n");
         orderRepo.findByActiveTrue()
             .map(o -> o.id() + ";" + o.creationDate() + "\n")
             .forEach(Unchecked.consumer(writer::write));

         System.out.println("Export DONE");
      } catch (Exception e) {
         System.out.println("Export FAILED: " + e); // TERROR-Driven Development
         // imagine... sendErrorEmail(e);
         throw e;
      } finally {
         long t1 = System.currentTimeMillis();
         System.out.println("Export completed in seconds: " + (t1 - t0) / 1000);
      }
   }
}


@RequiredArgsConstructor
public class LoanPattern {
  private final FileExportService fileExporterService;

  public void exportOrders() throws IOException {
    fileExporterService.exportOrders();
  }

  public void exportUsers() {
    // TODO implement the export of users using *the same workflow* as for orders
  }
}