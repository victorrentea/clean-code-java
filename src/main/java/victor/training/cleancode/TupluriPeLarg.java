package victor.training.cleancode;

import io.vavr.Tuple2;
import io.vavr.Tuple4;
import lombok.Data;
import lombok.Value;

import java.util.List;

public class TupluriPeLarg {


    public static void main(String[] args) {

//        Integer i1 = Integer.valueOf(10);
//        new Integer(10);

        Tuple4<Ticker, Long, String, List<Tuple2<String, Integer>>> t = null;
        System.out.println(t._4.get(0)._1);
    }
}
//@Data // evita
@Value // cat de mult se poate
class Ticker { // microtipuri - DOR la performanta, dar in cod nu mai e doar uin string, e un Ticker :p
   String value;

}
@Value
class UnTipNou {
    String ticker;

}

