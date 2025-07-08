package victor.training.cleancode;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import org.wiremock.spring.EnableWireMock;
import victor.training.cleancode.app.ProductService;
import victor.training.cleancode.support.*;

import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@EnableWireMock
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ProductService.class, RestTemplate.class})
@TestPropertySource(properties = "rapex.service.url.base=http://localhost:${wiremock.server.port:9999}")
class ProductServiceTest {
  @MockitoBean
  SupplierRepo supplierRepo;
  @MockitoBean
  ProductRepo productRepo;
  @Autowired
  ProductService productService;
  @Captor
  ArgumentCaptor<Product> productCaptor;

  @Test
  void createThrowsForUnsafeProduct() {
    ProductDto productDto = ProductDto.builder()
        .name("name")
        .barcode("Code1")
        .supplierCode("code2")
        .category(ProductCategory.HOME)
        .build();
    stubFor(get(urlEqualTo("/product/code1/safety"))
        .willReturn(okJson("{\"safetyClass\": \"UNSAFE\"}")));

    assertThatThrownBy(() -> productService.createProduct(productDto))
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("Product is not safe!");
  }

  @Test
  void createOk() {
    ProductDto productDto = ProductDto.builder()
        .name("name")
        .barcode("Code1")
        .supplierCode("code2")
        .category(ProductCategory.HOME)
        .build();
    when(supplierRepo.findByCode("code2")).thenReturn(Optional.of(new Supplier().setCode("code2")));
    when(productRepo.save(productCaptor.capture())).thenReturn(new Product().setId(123L));
    stubFor(get(urlEqualTo("/product/code1/safety"))
        .willReturn(okJson("{\"safetyClass\": \"SAFE\"}")));

    Long createdId = productService.createProduct(productDto);

    assertThat(createdId).isEqualTo(123L);
    assertThat(productCaptor.getValue())
        .returns("name", Product::getName)
        .returns("Code1", Product::getBarcode)
        .returns("code2", p -> p.getSupplier().getCode())
        .returns(ProductCategory.HOME, Product::getCategory);
  }
}