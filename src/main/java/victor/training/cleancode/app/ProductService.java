package victor.training.cleancode.app;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import victor.training.cleancode.support.*;

// TODO 4
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
  private final SupplierRepo supplierRepo;
  private final ProductRepo productRepo;
  private final RapexApiClient rapexApiClient;

  public Long createProduct(ProductDto productDto) {
    log.info("Creating product {}", productDto);

    var safe = rapexApiClient.isSafe(productDto.barcode());
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

