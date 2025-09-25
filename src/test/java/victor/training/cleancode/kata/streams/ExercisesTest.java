package victor.training.cleancode.kata.streams;

import org.junit.jupiter.api.MethodOrderer.MethodName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import victor.training.cleancode.kata.streams.Order.PaymentMethod;
import victor.training.cleancode.kata.streams.Order.Status;

import java.io.File;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.time.LocalDate.now;
import static java.time.LocalDate.parse;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static org.assertj.core.api.Assertions.assertThat;
import static victor.training.cleancode.kata.streams.Order.PaymentMethod.CARD;
import static victor.training.cleancode.kata.streams.Order.PaymentMethod.CASH_ON_DELIVERY;
import static victor.training.cleancode.kata.streams.Order.Status.CANCELLED;
import static victor.training.cleancode.kata.streams.Order.Status.COMPLETED;

@TestMethodOrder(MethodName.class)
class ExercisesTest {
  public static final LocalDate YESTERDAY = now().minusDays(1);
  private static final LocalDate TODAY = LocalDate.now();
  private final Exercises sut = new Exercises();

  @Test
  void p1_activeOrders() {
    Order order1 = new Order(COMPLETED)
        .total(10)
        .createdOn(TODAY)
        .paymentMethod(CARD);
    Order order2 = new Order(Status.PLACED);

    List<OrderDto> dtos = sut.p1_activeOrders(List.of(order1, order2));

    assertThat(dtos).hasSize(1);
    assertThat(dtos.get(0).status()).isEqualTo(COMPLETED);
    assertThat(dtos.get(0).totalPrice()).isEqualTo(10.0);
    assertThat(dtos.get(0).creationDate()).isEqualTo(TODAY);
    assertThat(dtos.get(0).paymentMethod()).isEqualTo(CARD);
  }

  @Test
  void p2_findOrderById_found() {
    Order o = new Order(1);
    List<Order> orders = List.of(o, new Order(2));
    // Note: AssertJ is preferred over JUnit assertions today
    Order actual = sut.p2_findOrderById(orders, 1);
    assertThat(actual).isEqualTo(o);
  }

  @Test
  void p2_findOrderById_notFound() {
    Order actual = sut.p2_findOrderById(List.of(new Order(7)), 9999);
    assertThat(actual).isNull();
  }

  @Test
  void p3_hasActiveOrders_returnsTrue() {
    boolean actual = sut.p3_hasActiveOrders(List.of(new Order(CANCELLED), new Order(COMPLETED)));
    assertThat(actual).isTrue();
  }

  @Test
  void p3_hasActiveOrders_returnsFalse() {
    boolean actual = sut.p3_hasActiveOrders(List.of(new Order(CANCELLED)));
    assertThat(actual).isFalse();
  }

  @Test
  void p4_maxPriceOrder_doesMax() {
    Order one = new Order().total(1);
    Order ten = new Order().total(10);
    Order actual = sut.p4_maxPriceOrder(List.of(one, ten));
    assertThat(actual).isEqualTo(ten);
  }

  @Test
  void p4_maxPriceOrder_returnsNullForNoOrders() {
    assertThat(sut.p4_maxPriceOrder(List.of())).isNull();
  }

  @Test
  void p4_maxPriceOrder_ignoresSpecialOffers() {
    Order order = new Order().total(1)
        .add(new OrderLine().specialOffer(true));
    assertThat(sut.p4_maxPriceOrder(List.of(order))).isNull();
  }

  @Test
  void p5_last3Orders() {
    Order a = new Order().createdOn(parse("2024-01-01")).returnReason("a");
    Order b = new Order().createdOn(parse("2024-01-02")).returnReason("b");
    Order c = new Order().createdOn(parse("2024-01-03")).returnReason("c");
    Order d = new Order().createdOn(parse("2024-01-04")).returnReason("d");

    List<String> actual = sut.p5_last3Orders(List.of(a, b, c, d));

    assertThat(actual).containsExactly("d", "c", "b");
  }

  @Test
  void p5_getLast3Orders_whenOnlyTwoOrders() {
    Order a = new Order().createdOn(parse("2024-01-01")).returnReason("a");
    Order b = new Order().createdOn(parse("2024-01-02")).returnReason("b");

    List<String> actual = sut.p5_last3Orders(List.of(a, b));

    assertThat(actual).containsExactly("b", "a");
  }

  @Test
  void p5_getLast3Orders_skipsMissingReasons() {
    Order a = new Order().createdOn(parse("2024-01-01"));
    Order b = new Order().createdOn(parse("2024-01-02")).returnReason("b");

    List<String> actual = sut.p5_last3Orders(List.of(a, b));

    assertThat(actual).containsExactly("b");
  }

  @Test
  void p5_getLast3Orders_whenNoOrders() {
    assertThat(sut.p5_last3Orders(List.of())).isEmpty();
  }

  @Test
  void p6_completedTotalSum() {
    Order a = new Order(COMPLETED).total(10);
    Order b = new Order(COMPLETED).total(1);
    Order c = new Order(CANCELLED).total(1);

    long actual = sut.p6_completedTotalSum(List.of(a, b, c));

    assertThat(actual).isEqualTo(11);
  }

  @Test
  void p6_completedTotalSum_rounding() {
    Order a = new Order(COMPLETED).total(10.5);
    Order b = new Order(COMPLETED).total(10.5);

    long actual = sut.p6_completedTotalSum(List.of(a, b));

    assertThat(actual).isEqualTo(21);
  }

  @Test
  void p7_productsSorted() {
    Product chair = new Product("Chair");
    Product table = new Product("Table");
    Order order1 = new Order()
        .add(new OrderLine(chair, 3));
    Order order2 = new Order()
        .add(new OrderLine(table, 1))
        .add(new OrderLine(chair, 1));

    List<Product> actual = sut.p7_productsSorted(List.of(order1, order2));

    assertThat(actual).containsExactly(chair, table);
  }

  @Test
  void p8_ordersGroupedByPaymentMethod() {
    Order a_card = new Order().paymentMethod(CARD);
    Order b_cash = new Order().paymentMethod(CASH_ON_DELIVERY);
    Order c_card = new Order().paymentMethod(CARD);

    Map<PaymentMethod, List<Order>> actual = sut.p8_ordersGroupedByPaymentMethod(List.of(a_card, b_cash, c_card));

    assertThat(actual.get(CASH_ON_DELIVERY)).containsExactly(b_cash);
    assertThat(actual.get(CARD)).containsExactly(a_card, c_card);
  }

  @Test
  void p9_productCount() {
    Product chair = new Product("Chair");
    Product table = new Product("Table");
    Order order1 = new Order()
        .add(new OrderLine(chair, 3));
    Order order2 = new Order()
        .add(new OrderLine(table, 1))
        .add(new OrderLine(chair, 1));

    Map<Product, Integer> actual = sut.p9_productCount(List.of(order1, order2));

    Map<Product, Integer> expected = Map.of(
        chair, 4,
        table, 1);
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void pA_productNames() {
    Product armchair = new Product("Armchair");
    Product chair = new Product("Chair");
    Product table = new Product("Table");

    Order order1 = new Order()
        .add(new OrderLine(chair, 3));
    Order order2 = new Order()
        .add(new OrderLine(armchair, 1))
        .add(new OrderLine(table, 1))
        .add(new OrderLine(chair, 1));

    String actual = sut.pA_productNames(List.of(order1, order2));
    assertThat(actual).isEqualTo("Armchair,Chair,Table");
  }


  @Test
  void pB_ordersByPaymentPerMonth_should_return_correct_grouping() {
    Order a = new Order().paymentMethod(CARD).createdOn(parse("2024-01-15"));
    Order b = new Order().paymentMethod(CASH_ON_DELIVERY).createdOn(parse("2024-01-07"));
    Order c = new Order().paymentMethod(CARD).createdOn(parse("2024-02-17"));
    List<Order> orders = List.of(a, b, c);

    Map<Month, Map<PaymentMethod, List<Order>>> result = sut.pB_ordersByPaymentPerMonth(orders);

    var expected = Map.of(
        JANUARY, Map.of(
            CARD, List.of(a),
            CASH_ON_DELIVERY, List.of(b)
        ),
        FEBRUARY, Map.of(
            CARD, List.of(c)
        )
    );
    assertThat(result).isEqualTo(expected);
  }

  @Test
  void blankLinesInFile() throws Exception {
    File file = new File("src/test/java/victor/training/cleancode/kata/streams/sample.csv");

    Set<Integer> actual = sut.pC_csvLinesInAllFilesInFolder(file);

    Set<Integer> expected = Set.of(1, 2, 3);
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void pDFib() {
    assertThat(sut.pD_fib(0, 6)).containsExactly(1, 1, 2, 3, 5, 8);
    assertThat(sut.pD_fib(5, 9)).containsExactly(8, 13, 21, 34);
  }
}
