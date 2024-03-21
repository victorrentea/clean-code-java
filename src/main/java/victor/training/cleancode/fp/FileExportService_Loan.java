//package victor.training.cleancode.fp;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.jooq.lambda.Unchecked;
//import victor.training.cleancode.fp.support.OrderRepo;
//
//import java.io.*;
//import java.util.function.Consumer;
//
//
// interface MyConsumer<T> {
//   void accept(T t) throws Exception;
//}
//@Slf4j
//@RequiredArgsConstructor
//public class FileExportService_Loan {
//   private final OrderRepo orderRepo;
//
//   // New Feature: export USERS the "same way" you exported ORDERS
//
//   public void exportOrders(String fileName, Consumer<Writer> contentWriter) throws IOException {
//      File file = new File("target/" + fileName);
//      log.info("Starting export into {} ...", file.getAbsolutePath());
//      long t0 = System.currentTimeMillis();
//      try (Writer writer = new FileWriter(file)) {
//
//         contentWriter.accept(writer);
//
//         log.info("Export DONE");
//      } catch (Exception e) {
//         log.error("Export FAILED!", e); // TERROR-Driven Development
//         // imagine... sendErrorEmail(e);
//         throw e;
//      } finally {
//         long t1 = System.currentTimeMillis();
//         log.info("Export completed in {} seconds ", (t1 - t0) / 1000);
//      }
//   }
//
//   // higher-order function (takes AND returns a function)
//   private static <T> Consumer<T> convert(MyConsumer<T> f) {
//      return v -> {
//         try {
//            f.accept(v);
//         } catch (Exception e) {
//            throw new RuntimeException(e);
//         }
//      };
//   }
//
//   private static void f(Writer writer, String string) {
//      try {
//         writer.write(string);
//      } catch (IOException e) {
//         throw new RuntimeException(e);
//      }
//   }
//}
//         // #1 calculating the data directly from DB *should* be faster
//         //    => MEASURE don't GUESS about performance; write benchmarks & test it
//         //    ! it IS a dangerous practice to download loads of data in Java and then .stream.filter.map
//         // #2 writing to files can bottleneck. Indeed. we used to use BufferedWriter
//         //    ,then SSD appeared => access time 1/100 vs HDD
//
