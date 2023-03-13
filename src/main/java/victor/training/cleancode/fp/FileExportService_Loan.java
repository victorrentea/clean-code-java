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

interface ConsumerAruncator<T> {
   void accept(T t) throws Exception;
}

@Slf4j
@RequiredArgsConstructor
public class FileExportService_Loan {
   private final OrderRepo orderRepo;


   // evita sa intorci functii ca rez din alte functii,

   // intoarce o functie care orice exceptie aruncata de functia data param
   // o imbraca intr-un runtime exception
   public static <T> Consumer<T> wrap(ConsumerAruncator<T> f) {
      return string -> {
         try {
            f.accept(string);
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      };
   }

   private static void writePtCaJavaENaspa(Writer writer, String s) {
      try {
         writer.write(s);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

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

   //   public void f() throws IOException {
   //      if (true) {
   ////         throw new IllegalArgumentException();// runtime / unchecked
   //         throw new IOException();// GU-NOI
   //      }
   //   }


}

