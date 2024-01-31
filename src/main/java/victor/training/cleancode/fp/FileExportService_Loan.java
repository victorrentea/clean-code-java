package victor.training.cleancode.fp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.lambda.Unchecked;
import victor.training.cleancode.fp.support.OrderRepo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.function.Consumer;

@Slf4j
@RequiredArgsConstructor
public class FileExportService_Loan {
   private final OrderRepo orderRepo;

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

   public void writeOrders(Writer writer) throws IOException {
      writer.write("order_id;date\n");
      orderRepo.findByActiveTrue()
          .map(o -> o.getId() + ";" + o.getCreationDate() + "\n")
          .forEach(Unchecked.consumer(writer::write));
   }

   private final UserRepo userRepo;

   public void writeUsers(Writer writer) throws IOException {
      writer.write("user_id;name\n");
       userRepo.findAll().stream()
           .map(o -> o.getId() + ";" + o.getName() + "\n")
           .forEach(Unchecked.consumer(writer::write));
   }
}

