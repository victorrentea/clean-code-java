package victor.training.cleancode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.ResourceBundle;

// TODO 8
public class StructuralReplace {
  private static final Logger log = LoggerFactory.getLogger(StructuralReplace.class);

  public void inLegacyCodebases(int i) {
    String name = "John";
    log.info(Lib.render(Lib.translate("key-2"), name, "2nd param"));
//    log.info(Library.renderTranslate("key-2", name, "Doe")); // result

    log.info(Lib.render(Lib.translate("key-" + i), name.toUpperCase()));
//    log.info(Library.renderTranslate("key-" + i, name.toUpperCase())); // result

    log.info(Lib.render("N/A", name));
//    log.info(Library.render("N/A", name)); // result no change as no nested call render(translate(
  }

  public String location2(String in) {
    return Lib.render(Lib.translate("key-1"), in.toUpperCase());
//    return Library.renderTranslate("key-1", in.toUpperCase()); // result
  }
  // Go to Edit > Find > Replace Structurally
  // Input: victor.training.cleancode.Library.render(victor.training.cleancode.Library.translate($Key$), $Parameters$)
  //  + set count to $Parameters$ 0..∞
  // Output: victor.training.cleancode.Library.renderTranslate($Key$, $Parameters$)
}

class Lib {
  public static ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");

  public static String renderTranslate(String key, String... values) {
    return render(translate(key), values);
  }

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
}
