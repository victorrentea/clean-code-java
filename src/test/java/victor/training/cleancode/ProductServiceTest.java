package victor.training.cleancode;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import victor.training.cleancode.support.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
  @Mock
  SupplierRepo supplierRepo;
  @Mock
  ProductRepo productRepo;
  @Mock
  SafetyApiAdapter safetyApiAdapter;
  @InjectMocks
  ProductService productService;

  @Captor
  ArgumentCaptor<Product> productCaptor;

  @Test
  void createThrowsForUnsafeProduct() {
    ProductDto productDto = ProductDto.builder()
        .name("name")
        .barcode("code1")
        .supplierCode("code2")
        .category(ProductCategory.HOME)
        .build();
    when(safetyApiAdapter.isSafe("code1")).thenReturn(false);

    assertThatThrownBy(() -> productService.createProduct(productDto))
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("Product is not safe!");
  }

  @Test
  void createOk() {
    ProductDto productDto = ProductDto.builder()
        .name("name")
        .barcode("code1")
        .supplierCode("code2")
        .category(ProductCategory.HOME)
        .build();
    when(supplierRepo.findByCode("code2")).thenReturn(Optional.of(new Supplier().setCode("code2")));
    when(safetyApiAdapter.isSafe("code1")).thenReturn(true);
    when(productRepo.save(any())).thenReturn(new Product().setId(123L));

    Long createdId = productService.createProduct(productDto);

    verify(productRepo).save(productCaptor.capture());
    assertThat(createdId).isEqualTo(123L);
    assertThat(productCaptor.getValue())
        .returns("name", Product::getName)
        .returns("code1", Product::getBarcode)
        .returns("code2", p -> p.getSupplier().getCode())
        .returns(ProductCategory.HOME, Product::getCategory);
  }
}