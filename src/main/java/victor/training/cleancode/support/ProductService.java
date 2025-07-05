package victor.training.cleancode.support;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
  public static final String PRODUCT_CREATED_TOPIC = "product-created";
  private final SupplierRepo supplierRepo;
  private final ProductRepo productRepo;
  private final SafetyApiAdapter safetyApiAdapter;

  public Long createProduct(ProductDto productDto) {
    log.info("Creating product {}", productDto);
    boolean safe = safetyApiAdapter.isSafe(productDto.barcode()); // ⚠️ REST call inside
    if (!safe) {
      throw new IllegalStateException("Product is not safe!");
    }
    if (productDto.category() == null) {
      productDto = productDto.withCategory(ProductCategory.UNCATEGORIZED);
    }
    Product product = new Product();
    product.setName(productDto.name());
    product.setBarcode(productDto.barcode());
    product.setCategory(productDto.category());
    product.setSupplier(supplierRepo.findByCode(productDto.supplierCode()).orElseThrow());
    Long productId = productRepo.save(product).getId();
    return productId;
  }

}
