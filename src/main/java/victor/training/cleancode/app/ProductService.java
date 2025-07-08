package victor.training.cleancode.app;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import victor.training.cleancode.support.*;

import java.net.URL;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
  private final SupplierRepo supplierRepo;
  private final ProductRepo productRepo;
  private final RestTemplate restTemplate;
  @Value("${safety.service.url.base}")
  private URL baseUrl;

  public Long createProduct(ProductDto productDto) {
    log.info("Creating product {}", productDto);
    SafetyResponse response = restTemplate.getForEntity(
            baseUrl + "/product/{barcode}/safety",
            SafetyResponse.class, productDto.barcode().toLowerCase())
        .getBody();
    boolean safe = "SAFE".equals(response.safetyClass());
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

  public record SafetyResponse(String safetyClass, String detailsUrl) {
  }

}
