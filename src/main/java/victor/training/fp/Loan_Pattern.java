package victor.training.fp;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jooq.lambda.Unchecked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.function.Consumer;
import java.util.stream.Stream;


interface MyConsumer<T> {


    void accept(T t) throws Exception;
}

interface OrderRepo extends JpaRepository<Order, Long> {
    Stream<Order> findByActiveTrue(); // Streaming query over 1 million orders
}

@Slf4j
@RequiredArgsConstructor
class FileExporter {
    private final OrderRepo orderRepo;

    public static <T> Consumer<T> convert(MyConsumer<T> consumerThrows) {
        return s -> {
            try {
                consumerThrows.accept(s);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public void exportOrders(Consumer<Writer> contentWriterFunction) throws IOException {
        File file = new File("target/orders.csv");
        log.info("Starting export into {} ...", file.getAbsolutePath());
        long t0 = System.currentTimeMillis();
        try (Writer writer = new FileWriter(file)) {
            contentWriterFunction.accept(writer); // DO NOT ABUSE only use for
            // cases in which you HAVE to be in a try { or for {

            log.info("Export DONE");
        } catch (Exception e) {
            log.error("Export FAILED!", e); // TERROR-Driven Development
            // imagine... sendErrorEmail(e);
            throw e;
        } finally {
            long t1 = System.currentTimeMillis();
            log.info("Export completed in {} seconds ", (t1 - t0) / 1000);
            // TODO victorrentea 21.06.2022:
        }
    }
}
//Stephan
@RequiredArgsConstructor
class ExportService {
    private final FileExporter fileExporter;
    @Autowired
    private OrderRepo orderRepo;



    @SneakyThrows
    public void exportOrders() {
        fileExporter.exportOrders(Unchecked.consumer(writer -> {
            writer.write("order_id;date\n");

            orderRepo.findByActiveTrue()
                    .map(o -> o.getId() + ";" + o.getCreationDate() + "\n")
                    .forEach(Unchecked.consumer(writer::write));
        }));
    }

    @SneakyThrows
    public void exportUsers() {
        // TODO implement the export of users using *the same workflow* as for orders
    }
}
