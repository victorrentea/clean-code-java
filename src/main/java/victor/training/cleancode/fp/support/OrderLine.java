package victor.training.cleancode.fp.support;

import lombok.Value;

public record OrderLine(Product product, int itemCount) {
}
