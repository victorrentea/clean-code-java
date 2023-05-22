package victor.training.cleancode.fp.support;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
//@Accessors(chain = true)
@ToString
public
class Product {

  @Id
  @GeneratedValue
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

}
