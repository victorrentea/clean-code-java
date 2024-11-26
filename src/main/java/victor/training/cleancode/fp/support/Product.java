package victor.training.cleancode.fp.support;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.beans.Transient;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public
class Product {

  private Long id;

  private String name;

  private ProductCategory category;

  private LocalDateTime createDate;
  private boolean premium;
  private boolean deleted;

//  @Transient
//  transient  double tempraryPrice;


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

  public Product setPremium(boolean premium) {
    this.premium = premium;
    return this;
  }
}
