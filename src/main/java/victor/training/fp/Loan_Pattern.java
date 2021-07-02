package victor.training.fp;

import lombok.extern.slf4j.Slf4j;
import org.jooq.lambda.Unchecked;
import org.jooq.lambda.fi.util.function.CheckedConsumer;
import org.jooq.lambda.fi.util.function.CheckedFunction;
import victor.training.cleancode.pretend.JpaRepository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.function.Consumer;
import java.util.stream.Stream;

// export all orders to a file

interface OrderRepo extends JpaRepository<Order, Long> { // Spring Data FanClub
	Stream<Order> findByActiveTrue(); // 1 Mln orders ;)
}

@Slf4j
class OrderExporter {

	private OrderRepo repo;
			
	public File exportFile(String fileName) throws IOException {
		File file = new File("export/" + fileName);
		try (Writer writer = new FileWriter(file)) {
			writer.write("OrderID;Date\n");
			repo.findByActiveTrue()
				.map(o -> o.getId() + ";" + o.getCreationDate())

				.forEach(Unchecked.consumer(writer::write));
			return file;
		} catch (Exception e) {
			// TODO send email notification
			log.debug("Gotcha!", e); // TERROR-Driven Development
			throw e;
		}
	}
}

