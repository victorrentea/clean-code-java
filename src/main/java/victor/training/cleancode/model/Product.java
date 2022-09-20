package victor.training.cleancode.model;

//VVVVVVVVV ==== supporting (dummy) code ==== VVVVVVVVV
public class Product {
    private Long id;
    private boolean deleted;

	public Product() {
	}

	public Long getId() {
		return this.id;
	}

	public boolean isDeleted() {
		return this.deleted;
	}

	public Product setId(Long id) {
		this.id = id;
		return this;
	}

	public Product setDeleted(boolean deleted) {
		this.deleted = deleted;
		return this;
	}

	public boolean equals(final Object o) {
		if (o == this) return true;
		if (!(o instanceof Product)) return false;
		final Product other = (Product) o;
		if (!other.canEqual((Object) this)) return false;
		final Object this$id = this.getId();
		final Object other$id = other.getId();
		if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
		if (this.isDeleted() != other.isDeleted()) return false;
		return true;
	}

	protected boolean canEqual(final Object other) {
		return other instanceof Product;
	}

	public int hashCode() {
		final int PRIME = 59;
		int result = 1;
		final Object $id = this.getId();
		result = result * PRIME + ($id == null ? 43 : $id.hashCode());
		result = result * PRIME + (this.isDeleted() ? 79 : 97);
		return result;
	}

	public String toString() {
		return "Product(id=" + this.getId() + ", deleted=" + this.isDeleted() + ")";
	}
}
