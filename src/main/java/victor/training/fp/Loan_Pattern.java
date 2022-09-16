package victor.training.fp;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jooq.lambda.Unchecked;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.function.Consumer;
import java.util.stream.Stream;

//class UserRepo {
//
////   public User findById(int userId) {
////      return null;
////   }
//}
@Slf4j
@RequiredArgsConstructor
class FileExporter {
   private final OrderRepo orderRepo;
//   private final UserRepo

   public void exportOrders(String fileName, Consumer<Writer> contentWriter) throws IOException {
      File file = new File("target/" + fileName + ".csv");
      log.info("Starting export into {} ...", file.getAbsolutePath());
      long t0 = System.currentTimeMillis();
      try (Writer writer = new FileWriter(file)) {

         contentWriter.accept(writer);

         log.info("Export DONE");
      } catch (Exception e) {
         log.error("Export FAILED!", e); // TERROR-Driven Development
         // imagine... sendErrorEmail(e);
         throw e;
      } finally {
         long t1 = System.currentTimeMillis();
         log.info("Export completed in {} seconds ", (t1 - t0) / 1000);
      }
   }

   @SneakyThrows
   public void writeOrderContent(Writer writer) {
      writer.write("order_id;date\n");
      orderRepo.findByActiveTrue()
          .map(o -> o.getId() + ";" + o.getCreationDate() + "\n")
          .forEach(Unchecked.consumer(writer::write));
   }
}

@RequiredArgsConstructor
class ExportService {
   private final FileExporter fileExporter;

   @SneakyThrows
   public void exportOrders() {
      fileExporter.exportOrders("orders", writer -> fileExporter.writeOrderContent(writer));
   }

   @SneakyThrows
   public void exportUsers() {
      fileExporter.exportOrders("users", writer -> {
         //         writer.write("username;date\n");
         //         userRepo.findAll().stream()
         //                 .map(o -> o.getUsenme() + ";" + o.getCreationDate() + "\n")
         //                 .forEach(Unchecked.consumer(writer::write));
      });
      // TODO implement the export of users using *the same workflow* as for orders
   }
}

interface OrderRepo extends JpaRepository<Order, Long> {
   Stream<Order> findByActiveTrue(); // Streaming query over 1 million orders
}
