package victor.training.cleancode;

import io.vavr.Tuple4;

import java.util.List;

public class TuplePlay {
    public static void main(String[] args) {
Object[] ohNO = {1L, "a"};
        Tuple4<Long, String, String, List<String>> ohGod = new Tuple4<>(1L, "SSN", "phone", List.of("nationalitity", "nationalitity2"));

        System.out.println("Calling " + ohGod._2);

    }
}
