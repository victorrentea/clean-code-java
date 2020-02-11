package victor.training.refactoring;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.DayOfWeek.MONDAY;


public class AmVenitDeLaTzagara {
    private static final Logger log = LoggerFactory.getLogger(AmVenitDeLaTzagara.class);

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("a.txt"))) {
            func(reader);
        }




        int min = Math.min(1, 2);

        System.out.println("Discount: " + getDiscount(MONDAY));
    }

    private static void func(BufferedReader reader) {
        try (Stream<String> lines = reader.lines()) {
            List<String> list = lines
                    .map(AmVenitDeLaTzagara::extract)
                    .collect(Collectors.toList());
        }
    }

    private static String extract(String s) {
        return s.toUpperCase();
    }

    private static int getDiscount(DayOfWeek day) {
        return day == DayOfWeek.SUNDAY ? 1 : 0;
    }
}
