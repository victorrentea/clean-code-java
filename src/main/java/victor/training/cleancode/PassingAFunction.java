package victor.training.cleancode;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import victor.training.cleancode.support.Order;
import victor.training.cleancode.support.OrderRepo;
import victor.training.cleancode.support.User;
import victor.training.cleancode.support.UserRepo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.function.Consumer;

@Slf4j
@RequiredArgsConstructor
class FileExporter {

   // garbage : resource management, I/O  - URGLY
   public void exportFile(final String fileName, Consumer<Writer> contentWriterFunction) throws IOException {
      File file = new File("target/" + fileName);
      log.info("Starting export into {} ...", file.getAbsolutePath());
      long t0 = System.currentTimeMillis();
      try (Writer writer = new FileWriter(file)) {

         contentWriterFunction.accept(writer);

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
class ExportFileContents {
   private final UserRepo userRepo;
   @SneakyThrows
   public void userExportContent(Writer writer) {
      writer.write("username;email\n"); // header
      for (User user : userRepo.findAll()) {
         writer.write(user.getUsername() + ";" + user.getEmail() + "\n"); // an order CSV line
      }
   }

   private final OrderRepo orderRepo;
   public void orderExportContent(Writer writer) {
      try {
         writer.write("OrderID;TotalPrice;Date\n"); // header
         for (Order o : orderRepo.findAll()) {
            writer.write(o.getId() + ";" + o.getTotalPrice() + ";" + o.getCreationDate() + "\n"); // an order CSV line
         }
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }
}

@RequiredArgsConstructor
public class PassingAFunction {
   private final FileExporter fileExporter;
   private final ExportFileContents exportFileContents;

   @SneakyThrows
   public void exportOrders() {
      fileExporter.exportFile("orders.csv", writer -> exportFileContents.orderExportContent(writer));
   }

   @SneakyThrows
   public void exportUsers() {
      fileExporter.exportFile("users.csv", new Consumer<Writer>() {
         @Override
         public void accept(Writer writer) {
//            Thread.sleep(10*3600*1000);
            exportFileContents.userExportContent(writer);
         }
      });
      // TODO implement me reusing *the same workflow* as for exporting orders
      // writing username and

      // Divergent Changes and passing functions: Does this make the Strategy Pattern obsolete?
      // Or is there still a great advantage using the classic strategy implementation?


   }
}









