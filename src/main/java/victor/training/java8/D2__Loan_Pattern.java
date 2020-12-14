package victor.training.java8;

import lombok.extern.slf4j.Slf4j;
import victor.training.cleancode.pretend.JpaRepository;

import java.util.stream.Stream;

// export all orders to a file

interface OrderRepo extends JpaRepository<Order, Long> { // Spring Data FanClub
	Stream<Order> findByActiveTrue(); // 1 Mln orders ;)
}

@Slf4j
class OrderExporter {
	
	private OrderRepo repo;
			
//	public File exportFile(String fileName) {
//		File file = new File("export/" + fileName);
//		try (Writer writer = new FileWriter(file)) {
//			writer.write("OrderID;Date\n");
//			repo.findByActiveTrue()
//				.map(o -> o.getId() + ";" + o.getCreationDate())
//				.forEach(writer::write);
//			return file;
//		} catch (Exception e) {
//			// TODO send email notification
//			log.debug("Gotcha!", e); // TERROR-Driven Development
//			throw e;
//		}
//	}
}

