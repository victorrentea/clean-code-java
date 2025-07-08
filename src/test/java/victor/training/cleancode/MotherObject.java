package victor.training.cleancode;

import victor.training.cleancode.support.ProductCategory;
import victor.training.cleancode.support.ProductDto;

public class MotherObject {
  public static ProductDto aProduct() {
    return ProductDto.builder()
        .name("name")
        .barcode("code1")
        .supplierCode("code2")
        .category(ProductCategory.HOME)
        .build();
  }
}
