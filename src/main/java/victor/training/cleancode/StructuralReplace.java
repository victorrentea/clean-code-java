package victor.training.cleancode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.ResourceBundle;

public class StructuralReplace {
  private static final Logger log = LoggerFactory.getLogger(StructuralReplace.class);

  public void location1() {
    String firstName = "John";
    log.info(Library.render(Library.translate("Hello %s %s"),
        firstName,
        "Doe"));
    log.info(Library.render(Library.translate("Hi " + "%s"), firstName.toUpperCase()));
    log.info(Library.render("No Template", firstName));

//    log.info(Library.renderTranslate("Hello %s %s", firstName, "Doe")); // result
//    log.info(Library.renderTranslate("Hi " + "%s", firstName.toUpperCase())); // result
//    log.info(Library.render("No Template", firstName)); // result
  }

  public String location2(String in) {
    return Library.render(Library.translate("Hi " + "%s"), in.toUpperCase());
//    return Library.renderTranslate("Hi " + "%s", in.toUpperCase()); // result
  }
  // Go to Edit > Find > Replace Structurally
  // Input: victor.training.cleancode.Library.render(victor.training.cleancode.Library.translate($Key$), $Parameters$)
  //  + set count to $Parameters$ 0..∞
  // Output: victor.training.cleancode.Library.renderTranslate($Key$, $Parameters$)
}

class Library {
  private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");

  public static String render(String template, String... values) {
    String[] transformedParams = Arrays.stream(values).map(java.lang.String::toUpperCase).toArray(String[]::new);
    return template.formatted((Object[]) transformedParams);
  }

  public static String translate(String messageKey) {
    if (resourceBundle.containsKey(resourceBundle.getString(messageKey))) {
      return resourceBundle.getString(messageKey);
    } else {
      return messageKey;
    }
  }

  public static String renderTranslate(String key, String... values) {
    return render(translate(key), values);
  }
}
