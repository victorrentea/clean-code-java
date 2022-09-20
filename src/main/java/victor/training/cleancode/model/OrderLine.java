package victor.training.cleancode.model;

public class OrderLine {
    private Product product;
    private int itemCount;

	public OrderLine() {
	}

	public Product getProduct() {
		return this.product;
	}

	public int getItemCount() {
		return this.itemCount;
	}

	public OrderLine setProduct(Product product) {
		this.product = product;
		return this;
	}

	public OrderLine setItemCount(int itemCount) {
		this.itemCount = itemCount;
		return this;
	}

	public boolean equals(final Object o) {
		if (o == this) return true;
		if (!(o instanceof OrderLine)) return false;
		final OrderLine other = (OrderLine) o;
		if (!other.canEqual((Object) this)) return false;
		final Object this$product = this.getProduct();
		final Object other$product = other.getProduct();
		if (this$product == null ? other$product != null : !this$product.equals(other$product)) return false;
		if (this.getItemCount() != other.getItemCount()) return false;
		return true;
	}

	protected boolean canEqual(final Object other) {
		return other instanceof OrderLine;
	}

	public int hashCode() {
		final int PRIME = 59;
		int result = 1;
		final Object $product = this.getProduct();
		result = result * PRIME + ($product == null ? 43 : $product.hashCode());
		result = result * PRIME + this.getItemCount();
		return result;
	}

	public String toString() {
		return "OrderLine(product=" + this.getProduct() + ", itemCount=" + this.getItemCount() + ")";
	}
}
