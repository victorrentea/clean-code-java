package victor.training.cleancode.model;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private Long id;
    private List<OrderLine> orderLines;
    private LocalDate creationDate;
    private boolean active;
    private int price;

	public Order() {
	}

	public Long getId() {
		return this.id;
	}

	public List<OrderLine> getOrderLines() {
		return this.orderLines;
	}

	public LocalDate getCreationDate() {
		return this.creationDate;
	}

	public boolean isActive() {
		return this.active;
	}

	public int getPrice() {
		return this.price;
	}

	public Order setId(Long id) {
		this.id = id;
		return this;
	}

	public Order setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
		return this;
	}

	public Order setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
		return this;
	}

	public Order setActive(boolean active) {
		this.active = active;
		return this;
	}

	public Order setPrice(int price) {
		this.price = price;
		return this;
	}

	public boolean equals(final Object o) {
		if (o == this) return true;
		if (!(o instanceof Order)) return false;
		final Order other = (Order) o;
		if (!other.canEqual((Object) this)) return false;
		final Object this$id = this.getId();
		final Object other$id = other.getId();
		if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
		final Object this$orderLines = this.getOrderLines();
		final Object other$orderLines = other.getOrderLines();
		if (this$orderLines == null ? other$orderLines != null : !this$orderLines.equals(other$orderLines))
			return false;
		final Object this$creationDate = this.getCreationDate();
		final Object other$creationDate = other.getCreationDate();
		if (this$creationDate == null ? other$creationDate != null : !this$creationDate.equals(other$creationDate))
			return false;
		if (this.isActive() != other.isActive()) return false;
		if (this.getPrice() != other.getPrice()) return false;
		return true;
	}

	protected boolean canEqual(final Object other) {
		return other instanceof Order;
	}

	public int hashCode() {
		final int PRIME = 59;
		int result = 1;
		final Object $id = this.getId();
		result = result * PRIME + ($id == null ? 43 : $id.hashCode());
		final Object $orderLines = this.getOrderLines();
		result = result * PRIME + ($orderLines == null ? 43 : $orderLines.hashCode());
		final Object $creationDate = this.getCreationDate();
		result = result * PRIME + ($creationDate == null ? 43 : $creationDate.hashCode());
		result = result * PRIME + (this.isActive() ? 79 : 97);
		result = result * PRIME + this.getPrice();
		return result;
	}

	public String toString() {
		return "Order(id=" + this.getId() + ", orderLines=" + this.getOrderLines() + ", creationDate=" + this.getCreationDate() + ", active=" + this.isActive() + ", price=" + this.getPrice() + ")";
	}
}
