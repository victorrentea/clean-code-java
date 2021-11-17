package victor.training.pure.purity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@ToString
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private ProductCategory category;

    private String barcode;

//    @ManyToOne
//    private Supplier supplier;

    private LocalDateTime createDate;
    public Product(String name, String barcode, ProductCategory category) {
        this.name = name;
        this.barcode = barcode;
        this.category = category;
    }

    public Product(String name) {
        this.name = name;
    }

    public Product() {}


}
