package victor.training.cleancode.fp.support;

import lombok.Value;

import java.util.List;

public record Customer(List<Coupon> coupons) {
}
