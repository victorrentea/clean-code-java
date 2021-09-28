package victor.training.fp;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jooq.lambda.Unchecked;
import victor.training.cleancode.pretend.JpaRepository;
import victor.training.cleancode.pretend.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.function.Consumer;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
class FileExporter {
   // Respo: opens file, measures time, handles errors :: la fel toate pentru toate exporturile
   public void exportCsv(final String fileName, Consumer<Writer> functie) throws IOException {
      File file = new File("export/" + fileName + ".csv");
      log.info("Starting export into {} ...", file.getAbsolutePath());
      long t0 = System.currentTimeMillis();
      try (Writer writer = new FileWriter(file)) {
         functie.accept(writer);
         log.info("Export completed in {} seconds ", (System.currentTimeMillis() - t0) / 1000);
      } catch (Exception e) {
         sendErrorEmail(e);
         log.debug("Gotcha!", e); // TODO TERROR-Driven Development
         throw e;
      }
   }

   private void sendErrorEmail(Exception e) {
      // complex code
   }
}
@Service
@RequiredArgsConstructor
class OrderContentWriter {
   private final OrderRepo orderRepo;

   public void write(Writer writer) {
      try {
         writer.write("OrderID;Date\n");
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
      orderRepo.findByActiveTrue()
          .map(o -> o.getId() + ";" + o.getCreationDate() + "\n")
          .forEach(Unchecked.consumer(writer::write));
   }
}
@Service
@RequiredArgsConstructor
class UserContentWriter {
   private final UserRepo userRepo;

   @SneakyThrows
   public void write(Writer writer) {
      writer.write("username;firstname\n");
      userRepo.findAll().stream()
          .map(u -> u.getUsername() + ";" + u.getFirstName())
          .forEach(Unchecked.consumer(writer::write));
   }
}

@RequiredArgsConstructor
class ExportService {
   private final FileExporter fileExporter;
   private final UserContentWriter userContentWriter;
   private final OrderContentWriter orderContentWriter;

   @SneakyThrows
   public void exportOrders() {
      fileExporter.exportCsv("orders", orderContentWriter::write);
   }

   @SneakyThrows
   public void exportUsers() {
      fileExporter.exportCsv("users", userContentWriter::write);
   }
}

interface OrderRepo extends JpaRepository<Order, Long> { // Spring Data FanClub
   Stream<Order> findByActiveTrue(); // 1 Mln orders ;)
}
