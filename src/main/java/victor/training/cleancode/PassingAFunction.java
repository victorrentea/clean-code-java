package victor.training.cleancode;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import victor.training.cleancode.support.Order;
import victor.training.cleancode.support.OrderRepo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
class FileExporter {
   private final OrderRepo orderRepo;

   public void exportOrder() throws IOException {
      File file = new File("target/orders.csv");
      log.info("Starting export into {} ...", file.getAbsolutePath());
      long t0 = System.currentTimeMillis();
      try (Writer writer = new FileWriter(file)) {

         writer.write("OrderID;TotalPrice;Date\n"); // header
         for (Order o : orderRepo.findAll()) {
            writer.write(o.getId() + ";" + o.getTotalPrice() + ";" + o.getCreationDate() + "\n"); // an order CSV line
         }

         log.info("Export DONE OK");
      } catch (Exception e) {
         log.error("Export FAILED", e);
         // imagine sendErrorEmail(e);
         throw e;
      } finally {
         long t1 = System.currentTimeMillis();
         log.info("Export finished in {} seconds ", (t1 - t0) / 1000);
      }
   }
}

@RequiredArgsConstructor
public class PassingAFunction {
   private final FileExporter fileExporter;

   @SneakyThrows
   public void exportOrders() {
      fileExporter.exportOrder();
   }

   @SneakyThrows
   public void exportUsers() {
      // TODO implement me reusing *the same workflow* as for exporting orders
      // writing username and
   }
}









