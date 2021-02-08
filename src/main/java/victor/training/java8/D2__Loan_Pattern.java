package victor.training.java8;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jooq.lambda.Unchecked;
import org.junit.Test;
import victor.training.cleancode.pretend.JpaRepository;
import victor.training.cleancode.pretend.Service;

import java.io.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

// export all orders to a file

interface OrderRepo extends JpaRepository<Order, Long> { // Spring Data FanClub
	Stream<Order> findByActiveTrue(); // 1 Mln orders ;)
}

@RequiredArgsConstructor
@Slf4j
@Service
class FileExporter {
	private final UserRepo userRepo;
//	@Value("")
	private String outputFolder;

	@FunctionalInterface
	public interface ContentWriter {
		void writeContent(Writer writer) throws Exception;
	}
	public File exportFile(String fileName, ContentWriter contentWriter) {
		File file = new File(outputFolder + "/" + fileName);
		try (Writer writer = new FileWriter(file)) {
			contentWriter.writeContent(writer);
			return file;
		} catch (Exception e) {
			throw new RuntimeException("Error writing to file " + file.getAbsolutePath(), e);
		}
	}
}
@Service
@RequiredArgsConstructor
class OrderContentWriter {
	private final OrderRepo repo;

//	@SneakyThrows
	public void writeOrders(Writer writer) throws IOException {
		writer.write("OrderID;Date\n");
		repo.findByActiveTrue()
			.map(o -> o.getId() + ";" + o.getCreationDate())
			.forEach(Unchecked.consumer(writer::write));
	}
}
@Service
@RequiredArgsConstructor
class UserContentWriter {
	private final UserRepo userRepo;

	@SneakyThrows
	public void writeUsers(Writer writer)  {
		writer.write("Username;user\n");
		userRepo.findAll()
			.stream()
			.map(u -> u.getUsername() + ";" + u.getLastName())
			.forEach(Unchecked.consumer(writer::write));
	}
}
class UserContentWriterTest {
	@Test
	public void test() throws IOException {
		UserContentWriter writer = new UserContentWriter(null);
		StringWriter sw = new StringWriter();
		writer.writeUsers(sw);
		String fileText = sw.toString();
		assertEquals("header,footer...", fileText);
	}
}

@RequiredArgsConstructor
@Service
class ExportService {
	private final FileExporter fileExporter;
	private final OrderContentWriter orderContentWriter;
	private final UserContentWriter userContentWriter;

	public void method() {
	    fileExporter.exportFile("orders.csv", orderContentWriter::writeOrders);
	    fileExporter.exportFile("users.csv", userContentWriter::writeUsers);
	}

}



