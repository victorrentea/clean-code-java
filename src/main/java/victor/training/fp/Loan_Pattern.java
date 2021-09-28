package victor.training.fp;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jooq.lambda.Unchecked;
import victor.training.cleancode.pretend.JpaRepository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
class FileExporter {
   private final OrderRepo orderRepo;

   public void exportOrder() throws IOException {
      File file = new File("target/orders.csv");
      log.info("Starting export into {} ...", file.getAbsolutePath());
      long t0 = System.currentTimeMillis();
      try (Writer writer = new FileWriter(file)) {
         writer.write("OrderID;Date\n");
         orderRepo.findByActiveTrue()
             .map(o -> o.getId() + ";" + o.getCreationDate())
             .forEach(Unchecked.consumer(writer::write));

         log.info("Export completed in {} seconds ", (System.currentTimeMillis() - t0) / 1000);
      } catch (Exception e) {
         sendErrorEmail(e);
         log.debug("Gotcha!", e); // TERROR-Driven Development
         throw e;
      }
   }

   private void sendErrorEmail(Exception e) {
      // complex code
   }
}

@RequiredArgsConstructor
class ExportService {
   private final FileExporter fileExporter;

   @SneakyThrows
   public void exportOrders() {
      fileExporter.exportOrder();
   }

   @SneakyThrows
   public void exportUsers() {
      // TODO
   }
}

interface OrderRepo extends JpaRepository<Order, Long> { // Spring Data FanClub
   Stream<Order> findByActiveTrue(); // 1 Mln orders ;)
}
