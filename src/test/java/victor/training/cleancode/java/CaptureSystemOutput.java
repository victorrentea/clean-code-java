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

package victor.training.cleancode.java;

import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ExtensionContext.Store;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * {@code @CaptureSystemOutput} is a JUnit JUpiter extension for capturing
 * output to {@code System.out} and {@code System.err}
 *
 * <strong>Example Usage</strong>
 *
 * <pre style="code">
 * {@literal @}Test
 * {@literal @}CaptureSystemOutput
 * void systemOut(OutputCapture outputCapture) {
 *     System.out.println("Printed to System.out!");
 *     assertThat(outputCapture.toString()).contains("System.out!");
 * }
 * </pre>
 *
 * <p>Based on code from Spring Boot's
 * <a href="https://github.com/spring-projects/spring-boot/blob/d3c34ee3d1bfd3db4a98678c524e145ef9bca51c/spring-boot-project/spring-boot-tools/spring-boot-test-support/src/main/java/org/springframework/boot/testsupport/rule/OutputCapture.java">OutputCapture</a>
 * rule for JUnit 4 by Phillip Webb and Andy Wilkinson.
 */
@Target({TYPE, METHOD})
@Retention(RUNTIME)
@Documented
@ExtendWith(CaptureSystemOutput.Extension.class)
public @interface CaptureSystemOutput {

  class Extension implements BeforeEachCallback, AfterEachCallback, ParameterResolver {
    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
      getOutputCapture(context).captureOutput();
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
      OutputCapture outputCapture = getOutputCapture(context);
      outputCapture.releaseOutput();
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
      boolean isTestMethodLevel = extensionContext.getTestMethod().isPresent();
      boolean isOutputCapture = parameterContext.getParameter().getType() == OutputCapture.class;
      return isTestMethodLevel && isOutputCapture;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
      return getOutputCapture(extensionContext);
    }

    private OutputCapture getOutputCapture(ExtensionContext context) {
      Namespace namespace = Namespace.create(getClass(), context.getRequiredTestMethod());
      Store store = context.getStore(namespace);
      return store.getOrComputeIfAbsent(OutputCapture.class);
    }
  }

  class OutputCapture {
    private CaptureOutputStream captureOut;
    private CaptureOutputStream captureErr;
    private ByteArrayOutputStream copy;

    void captureOutput() {
      this.copy = new ByteArrayOutputStream();
      this.captureOut = new CaptureOutputStream(System.out, copy);
      this.captureErr = new CaptureOutputStream(System.err, copy);
      System.setOut(new PrintStream(captureOut));
      System.setErr(new PrintStream(captureErr));
    }

    void releaseOutput() {
      System.setOut(captureOut.getOriginal());
      System.setErr(captureErr.getOriginal());
      this.copy = null;
    }

    private void flush() {
      try {
        captureOut.flush();
        captureErr.flush();
      } catch (IOException ex) {
        // ignore
      }
    }

    /**
     * Return all captured output to {@code System.out} and {@code System.err}
     * as a single string.
     */
    @Override
    public String toString() {
      flush();
      return copy.toString();
    }

    private static class CaptureOutputStream extends OutputStream {
      private final PrintStream original;
      private final OutputStream copy;

      CaptureOutputStream(PrintStream original, OutputStream copy) {
        this.original = original;
        this.copy = copy;
      }

      PrintStream getOriginal() {
        return original;
      }

      @Override
      public void write(int b) throws IOException {
        copy.write(b);
        original.write(b);
        original.flush();
      }

      @Override
      public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
      }

      @Override
      public void write(byte[] b, int off, int len) throws IOException {
        copy.write(b, off, len);
        original.write(b, off, len);
      }

      @Override
      public void flush() throws IOException {
        copy.flush();
        original.flush();
      }
    }
  }
}