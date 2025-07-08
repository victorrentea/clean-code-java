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

// TODO 2
@EnableWireMock
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ProductService.class, RestTemplate.class})
@TestPropertySource(properties = "rapex.service.url.base=http://localhost:${wiremock.server.port:9999}")
class ProductServiceTest {
  public static final String SUPPLIER_CODE = "code2";
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
    ProductDto productDto = MotherObject.aProduct();
    stubFor(get(urlEqualTo("/product/code1/safety"))
        .willReturn(okJson("{\"safetyClass\": \"UNSAFE\"}")));

    assertThatThrownBy(() -> productService.createProduct(productDto))
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("Product is not safe!");
  }

  @Test
  void createOk() {
    ProductDto productDto = MotherObject.aProduct().withName("different");
    when(supplierRepo.findByCode(SUPPLIER_CODE)).thenReturn(Optional.of(new Supplier().setCode(SUPPLIER_CODE)));
    when(productRepo.save(productCaptor.capture())).thenReturn(new Product().setId(123L));
    stubFor(get(urlEqualTo("/product/code1/safety"))
        .willReturn(okJson("{\"safetyClass\": \"SAFE\"}")));

    Long createdId = productService.createProduct(productDto);

    assertThat(createdId).isEqualTo(123L);
    assertThat(productCaptor.getValue())
        .returns("different", Product::getName)
        .returns("code1", Product::getBarcode)
        .returns(SUPPLIER_CODE, p -> p.getSupplier().getCode())
        .returns(ProductCategory.HOME, Product::getCategory);
  }
}