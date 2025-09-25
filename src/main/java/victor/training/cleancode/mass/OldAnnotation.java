package victor.training.cleancode.mass;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME) // stops javac from removing it at compilation
public @interface OldAnnotation {
  String name() default "";
}
