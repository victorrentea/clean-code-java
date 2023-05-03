package victor.training.cleancode.fp.support;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDateTime;

@Entity // sfantul Domain Model
@Getter
@Setter
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

  // TEMPORARY FIELD CODE SMELL
  // nu ajunge acest camp niciodata in DB. e doar lasat aiciea din faza 1 pt faza 2 a UC 323
  // nimeni in tot codul nu are nevoie acest camp
  // PRODUC PANICA
  //  transient private Double initialPriceDoarPanaAplicCupoanele;


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
