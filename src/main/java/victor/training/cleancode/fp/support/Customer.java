package victor.training.cleancode.fp.support;

import lombok.Data;
import org.apache.tomcat.jni.Address;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public
class Customer {
  private Long id;
  private String name;
  private String email;
  private List<String> labels = new ArrayList<>();
  private Address address = new Address();
  private Date createDate;
  private List<Coupon> coupons = new ArrayList<>();
}
