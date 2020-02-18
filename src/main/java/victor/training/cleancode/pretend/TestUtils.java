package victor.training.cleancode.pretend;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.concurrent.Callable;

public class TestUtils {
    public static String output(Runnable runnable) {
        PrintStream originalOut = System.out;
        try {
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                try (PrintStream newOut = new PrintStream(baos)) {
                    System.setOut(newOut);
                    runnable.run();
                }
                return baos.toString();
            }
        } catch (IOException e) {
            return e.getMessage();
        } finally {
            System.setOut(originalOut);
        }
    }
}
