package victor.training.cleancode.support;

import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
  public ProductDto toDto(Product product) {
    return ProductDto.builder()
        .id(product.getId())
        .supplierCode(product.getSupplier().getCode())
        .name(product.getName())
        .barcode(product.getBarcode())
        .category(product.getCategory())
        .createdDate(product.getCreatedDate())
        .build();
  }
}
