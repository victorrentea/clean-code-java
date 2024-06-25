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

import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ExtensionContext.Store;
import victor.training.testing.tools.CaptureSystemOutput.OutputCapture;


/**
 * JUnit Jupiter extension for capturing output to {@code System.out} and
 * {@code System.err} with expectations supported via Hamcrest matchers.
 *
 * <p>Based on code from Spring Boot's
 * <a href="https://github.com/spring-projects/spring-boot/blob/d3c34ee3d1bfd3db4a98678c524e145ef9bca51c/spring-boot-project/spring-boot-tools/spring-boot-test-support/src/main/java/org/springframework/boot/testsupport/rule/OutputCapture.java">OutputCapture</a>
 * rule for JUnit 4 by Phillip Webb and Andy Wilkinson.
 *
 * @author Sam Brannen
 * @author Phillip Webb
 * @author Andy Wilkinson
 */
class CaptureSystemOutputExtension implements BeforeEachCallback, AfterEachCallback, ParameterResolver {

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        getOutputCapture(context).captureOutput();
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
    }
//test
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
        return getStore(context).getOrComputeIfAbsent(OutputCapture.class);
    }

    private Store getStore(ExtensionContext context) {
        return context.getStore(Namespace.create(getClass(), context.getRequiredTestMethod()));
    }

}