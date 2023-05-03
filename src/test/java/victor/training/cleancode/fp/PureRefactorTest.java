package victor.training.cleancode.fp;

import org.junit.jupiter.api.Test;
import org.powermock.api.mockito.PowerMockito;
import victor.training.cleancode.fp.support.Product;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class PureRefactorTest {

  @Test
  void calculatePrices() throws NoSuchMethodException {
    //    PureRefactor.doCalculatePrices(new Product("P1", ProductCategory.BOOK, 10.0, false));
    Method f = PureRefactor.class.getDeclaredMethod("doCalculatePrices");

    //    PowerMockito.invokeMethod(obj, "privateMethodName");
    //    f.invoke(null, "doCalculatePrices", new ArrayList<>(), new HashMap<>(), new ArrayList<>());

  }
}