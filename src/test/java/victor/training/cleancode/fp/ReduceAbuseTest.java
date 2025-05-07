package victor.training.cleancode.fp;

import org.junit.jupiter.api.Test;
import victor.training.cleancode.fp.support.Order;
import victor.training.cleancode.fp.support.OrderLine;
import victor.training.cleancode.fp.support.Product;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ReduceAbuseTest {

    private final E3_ReduceAbuse sut = new E3_ReduceAbuse();

    @Test
    void noPremiumOrders() {
        Order normalOrder = new Order().setPrice(100);
        List<Order> orderList = List.of(normalOrder);

        var result = sut.getLastPremiumOrder(orderList);

        assertThat(result).isNull();
    }

    @Test
    void onePremiumOrder() {
        Order premiumOrder = new Order()
                .setOrderLines(List.of(new OrderLine(new Product().setPremium(true), 1)))
                .setCreationDate(LocalDate.of(2023, 3, 5));

        List<Order> orderList = List.of(premiumOrder);

        var result = sut.getLastPremiumOrder(orderList);

        assertThat(result).isEqualTo(premiumOrder);
    }

    @Test
    void multiplePremiumOrders() {
        Order premiumOrder1 = new Order()
                .setOrderLines(List.of(new OrderLine(new Product().setPremium(true), 1)))
                .setCreationDate(LocalDate.of(2023, 3, 4));

        Order premiumOrder2 = new Order()
                .setOrderLines(List.of(new OrderLine(new Product().setPremium(true), 1)))
                .setCreationDate(LocalDate.of(2023, 3, 5));

        List<Order> orderList = List.of(premiumOrder1, premiumOrder2);

        var result = sut.getLastPremiumOrder(orderList);

        assertThat(result).isEqualTo(premiumOrder2);
    }
}