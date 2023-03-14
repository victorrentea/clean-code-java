package videostore.horror;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

public enum PriceCode {
  CHILDREN
  //          {
  //    @Override
  //    public double computePrice(int days) {
  //      return 0; // pt calcule putine, merge;
  //      // NU CUMVA SA INJECTEZI DEP IN ENUM
  //    }
  //  }
  ,
  REGULAR,
  NEW_RELEASE,
  //  DE_BABACI
  ;

  //  public abstract double computePrice(int days);
}
