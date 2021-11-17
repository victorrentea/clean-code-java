package victor.training.pure.purity;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Customer {
	private Long id;
	private String name;
	private String email;
	private List<String> labels = new ArrayList<>();
	private Date createDate;
	private List<Coupon> coupons = new ArrayList<>();
}
