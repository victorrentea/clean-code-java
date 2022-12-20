package victor.training.cleancode.fp;

import io.vavr.collection.Stream.Cons;
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

@Slf4j
@RequiredArgsConstructor
class FileExporter {
   private final OrderRepo orderRepo;

   public void exportOrders() throws IOException {
      File file = new File("target/orders.csv");
      log.info("Starting export into {} ...", file.getAbsolutePath());
      long t0 = System.currentTimeMillis();
      try (Writer writer = new FileWriter(file)) {

         writer.write("order_id;date\n");
         orderRepo.findByActiveTrue()
             .map(o -> o.getId() + ";" + o.getCreationDate() + "\n")
//             .forEach(wrap(writer::write));
             .forEach(Unchecked.consumer(writer::write));
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
   interface ThrowingConsumer<T> {
      void accept(T t) throws  Exception;
   }
   // tre sa rearunce orice ex checked in aruncator() invelita intr-un RuntimeEx
   public static <T> Consumer<T> wrap(ThrowingConsumer<T> aruncator) {
      return t -> {
         try {
            aruncator.accept(t);
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      };
      // bad practice sa scrii fct din astea prea des.
      // da brain damage dev average din Java
   }


}

@RequiredArgsConstructor
class ExportService {
   private final FileExporter fileExporter;

   @SneakyThrows
   public void exportOrders() {
      fileExporter.exportOrders();
   }

   @SneakyThrows
   public void exportUsers() {
      // TODO implement the export of users using *the same workflow* as for orders
   }
}

interface OrderRepo extends JpaRepository<Order, Long> {
   Stream<Order> findByActiveTrue(); // Streaming query over 1 million orders
}
