/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package victor.training.testing.tools;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * {@code @CaptureSystemOutput} is a JUnit JUpiter extension for capturing
 * output to {@code System.out} and {@code System.err} with expectations
 * supported via Hamcrest matchers.
 *
 * <strong>Example Usage</strong>
 *
 * <pre style="code">
 * {@literal @}Test
 * {@literal @}CaptureSystemOutput
 * void systemOut(OutputCapture outputCapture) {
 *     outputCapture.expect(containsString("System.out!"));
 *
 *     System.out.println("Printed to System.out!");
 * }
 *
 * {@literal @}Test
 * {@literal @}CaptureSystemOutput
 * void systemErr(OutputCapture outputCapture) {
 *     outputCapture.expect(containsString("System.err!"));
 *
 *     System.err.println("Printed to System.err!");
 * }
 * </pre>
 *
 * <p>Based on code from Spring Boot's
 * <a href="https://github.com/spring-projects/spring-boot/blob/d3c34ee3d1bfd3db4a98678c524e145ef9bca51c/spring-boot-project/spring-boot-tools/spring-boot-test-support/src/main/java/org/springframework/boot/testsupport/rule/OutputCapture.java">OutputCapture</a>
 * rule for JUnit 4 by Phillip Webb and Andy Wilkinson.
 *
 * @author Sam Brannen
 * @author Phillip Webb
 * @author Andy Wilkinson
 */
@Target({ TYPE, METHOD })
@Retention(RUNTIME)
@Documented
@ExtendWith(CaptureSystemOutputExtension.class)
public @interface   CaptureSystemOutput {

    /**
     * {@code OutputCapture} captures output to {@code System.out} and {@code System.err}.
     *
     * <p>To obtain an instance of {@code OutputCapture}, declare a parameter of type
     * {@code OutputCapture} in a JUnit Jupiter {@code @Test}, {@code @BeforeEach},
     * or {@code @AfterEach} method.
     *
     * <p>{@linkplain #expect Expectations} are supported via Hamcrest matchers.
     *
     * <p>To obtain all output to {@code System.out} and {@code System.err}, simply
     * invoke {@link #toString()}.
     *
     * @author Phillip Webb
     * @author Andy Wilkinson
     * @author Sam Brannen
     */
    class OutputCapture {

        final List<Matcher<? super String>> matchers = new ArrayList<>();

        private CaptureOutputStream captureOut;

        private CaptureOutputStream captureErr;

        private ByteArrayOutputStream copy;

        void captureOutput() {
            this.copy = new ByteArrayOutputStream();
            this.captureOut = new CaptureOutputStream(System.out, this.copy);
            this.captureErr = new CaptureOutputStream(System.err, this.copy);
            System.setOut(new PrintStream(this.captureOut));
            System.setErr(new PrintStream(this.captureErr));
        }

        void releaseOutput() {
            System.setOut(this.captureOut.getOriginal());
            System.setErr(this.captureErr.getOriginal());
            this.copy = null;
        }

        private void flush() {
            try {
                this.captureOut.flush();
                this.captureErr.flush();
            }
            catch (IOException ex) {
                // ignore
            }
        }

        /**
         * Verify that the captured output is matched by the supplied {@code matcher}.
         *
         * <p>Verification is performed after the test method has executed.
         *
         * @param matcher the matcher
         */
        public void expect(Matcher<? super String> matcher) {
            this.matchers.add(matcher);
        }

        /**
         * Return all captured output to {@code System.out} and {@code System.err}
         * as a single string.
         */
        @Override
        public String toString() {
            flush();
            return this.copy.toString();
        }

        private static class CaptureOutputStream extends OutputStream {

            private final PrintStream original;

            private final OutputStream copy;

            CaptureOutputStream(PrintStream original, OutputStream copy) {
                this.original = original;
                this.copy = copy;
            }

            PrintStream getOriginal() {
                return this.original;
            }

            @Override
            public void write(int b) throws IOException {
                this.copy.write(b);
                this.original.write(b);
                this.original.flush();
            }

            @Override
            public void write(byte[] b) throws IOException {
                write(b, 0, b.length);
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                this.copy.write(b, off, len);
                this.original.write(b, off, len);
            }

            @Override
            public void flush() throws IOException {
                this.copy.flush();
                this.original.flush();
            }

        }

    }

}