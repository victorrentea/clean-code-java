package victor.training.cleancode.support;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.With;

import java.time.LocalDate;

@With
@Builder
public record ProductDto(
    Long id,
    @NotNull String name,
    @NotNull String barcode,
    String supplierCode,
    ProductCategory category,
    LocalDate createdDate) {
}