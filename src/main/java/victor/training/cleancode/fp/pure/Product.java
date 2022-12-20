package victor.training.cleancode.fp.pure;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
class Product {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  private ProductCategory category;

  private LocalDateTime createDate;
  private boolean premium;

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
}
