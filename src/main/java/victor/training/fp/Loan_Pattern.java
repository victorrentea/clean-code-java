//package victor.training.fp;
//
//import lombok.RequiredArgsConstructor;
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
//import org.jooq.lambda.Unchecked;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.Writer;
//import java.util.function.Consumer;
//import java.util.stream.Stream;
//
//interface OrderRepo extends JpaRepository<Order, Long> {
//    Stream<Order> findByActiveTrue(); // Streaming query over 1 million orders
//}
//interface UserRepo {}
//@Slf4j
//@RequiredArgsConstructor
//class FileExporter {
//    private final OrderRepo orderRepo;
//    private final UserRepo userRepo;
//
//    public void exportOrders(String fileName, Consumer<Writer> functieCareScrieContentul) throws IOException {
//        File file = new File("target/" + fileName + ".csv");
//        log.info("Starting export into {} ...", file.getAbsolutePath());
//        long t0 = System.currentTimeMillis();
//        try (Writer writer = new FileWriter(file)) {
//
////            exportOrders(writer);
////            exportUsers(writer);
//            functieCareScrieContentul.accept(writer);
//
//            log.info("Export DONE");
//        } catch (Exception e) {
//            log.error("Export FAILED!", e); // TERROR-Driven Development
//            // imagine... sendErrorEmail(e);
//            throw e;
//        } finally {
//            long t1 = System.currentTimeMillis();
//            log.info("Export completed in {} seconds ", (t1 - t0) / 1000);
//        }
//    }
//
//    private void exportOrders(Writer writer) throws IOException {
//        writer.write("order_id;date\n");
//
//        orderRepo.findByActiveTrue()
//                .map(o -> o.getId() + ";" + o.getCreationDate() + "\n")
//                .forEach(Unchecked.consumer(writer::write));
//    }
//
//    private static  <T> Consumer<T> f(ConsumerAruncator<T> aruncator) {
//        return s -> {
//            try {
//                aruncator.accept(s);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        };
//    }
//
//    private void writeSafely(Writer writer, String str) {
//        try {
//            writer.write(str);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public interface ConsumerAruncator<T> {
//        void accept(T t) throws Exception;
//    }
//}
//
//@RequiredArgsConstructor
//class ExportService {
//    private final FileExporter fileExporter;
//
//    @SneakyThrows
//    public void exportOrders() {
//        fileExporter.exportOrders("orders");
//    }
//
//    @SneakyThrows
//    public void exportUsers() {
//        // TODO implement the export of users using *the same workflow* as for orders
//    }
//}
