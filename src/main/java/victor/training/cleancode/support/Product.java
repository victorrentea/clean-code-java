package victor.training.cleancode.support;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Getter
@Setter
@ToString
public class Product {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  @Enumerated(STRING)
  private ProductCategory category;

  private String barcode;

  @ManyToOne
  private Supplier supplier;

  @CreatedDate
  // Spring assigns this at creation time: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#auditing
  private LocalDate createdDate;

  @CreatedBy // Spring assigns this at creation time from SecurityContext
  private String createdBy;

  public Product(String name, String barcode, ProductCategory category) {
    this.name = name;
    this.barcode = barcode;
    this.category = category;
  }

  public Product(String name) {
    this.name = name;
  }

  public Product() {
  }


}
