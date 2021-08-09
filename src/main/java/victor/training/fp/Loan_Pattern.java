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
class FileExporter { // technical aspects of writing files = === garbage code

   public void exportFile(String fileName, Consumer<Writer> contentWriter) throws IOException {
      File file = new File("export/" + fileName); //
      log.info("Starting export into {} ...", file.getAbsolutePath()); //
      long t0 = System.currentTimeMillis(); //
      try (Writer writer = new FileWriter(file)) {

         contentWriter.accept(writer);

         log.info("Export completed in {} seconds ", (System.currentTimeMillis() - t0) / 1000);//
      } catch (Exception e) {
         sendErrorEmail(e);
         throw e;
      }
   }

   private void sendErrorEmail(Exception e) {
      // complex code
   }
}

@RequiredArgsConstructor
class UserExportWriter {
   private final UserRepo userRepo;
   @SneakyThrows
   public void writeUsers(Writer writer)  {
      writer.write("username;firstname\n");

      userRepo.findAll().stream()
          .map(u -> u.getUsername() + ";" + u.getFirstName())
          .forEach(Unchecked.consumer(writer::write));
   }
}
@Service
@RequiredArgsConstructor
class OrderExportWriter {
   private final OrderRepo orderRepo;
   @SneakyThrows
   public void writeOrders(Writer writer) {
      writer.write("OrderID;Date\n");
      orderRepo.findByActiveTrue()
          .map(o -> o.getId() + ";" + o.getCreationDate())
          .forEach(Unchecked.consumer(writer::write));
   }

}

@Service
@RequiredArgsConstructor
class ExportService {
   private final FileExporter fileExporter;
   private final OrderExportWriter orderExportWriter;
   private final UserExportWriter userExportWriter;

   @SneakyThrows
   public void exportOrders() {
      fileExporter.exportFile("orders.csv",
          writer -> orderExportWriter.writeOrders(writer));
   }


   @SneakyThrows
   public void exportUsers() {
      fileExporter.exportFile("users.csv",
          writer -> userExportWriter.writeUsers(writer));
   }


}

interface OrderRepo extends JpaRepository<Order, Long> { // Spring Data FanClub
   Stream<Order> findByActiveTrue(); // 1 Mln orders ;)
}
