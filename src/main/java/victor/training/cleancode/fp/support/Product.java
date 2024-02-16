package victor.training.cleancode.fp.support;

import java.time.LocalDateTime;

public
class Product {

  private Long id;

  private String name;

  private ProductCategory category;

  private LocalDateTime createDate;
  private boolean premium;
  private boolean deleted;


  public Product(String name, ProductCategory category) {
    this.name = name;
    this.category = category;
  }

  public Product(String name) {
    this.name = name;
  }

  public Product() {
  }


  public boolean isPremium() {
    return premium;
  }

  public void setPremium(boolean premium) {
    this.premium = premium;
  }

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public ProductCategory getCategory() {
    return this.category;
  }

  public LocalDateTime getCreateDate() {
    return this.createDate;
  }

  public boolean isDeleted() {
    return this.deleted;
  }

  public Product setId(Long id) {
    this.id = id;
    return this;
  }

  public Product setName(String name) {
    this.name = name;
    return this;
  }

  public Product setCategory(ProductCategory category) {
    this.category = category;
    return this;
  }

  public Product setCreateDate(LocalDateTime createDate) {
    this.createDate = createDate;
    return this;
  }

  public Product setDeleted(boolean deleted) {
    this.deleted = deleted;
    return this;
  }

  public String toString() {
    return "Product(id=" + this.getId() + ", name=" + this.getName() + ", category=" + this.getCategory() + ", createDate=" + this.getCreateDate() + ", premium=" + this.isPremium() + ", deleted=" + this.isDeleted() + ")";
  }
}
