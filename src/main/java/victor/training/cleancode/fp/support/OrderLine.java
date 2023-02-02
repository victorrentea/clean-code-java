package victor.training.cleancode.fp.support;

import lombok.Data;
import victor.training.cleancode.fp.support.Product;

@Data
public class OrderLine {
  private Product product;
  private int itemCount;
}
