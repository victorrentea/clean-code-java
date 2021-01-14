package victor.training.mindit;

import org.apache.commons.lang.StringUtils;

public class CommunicationView {
   private final String comMsg;
   private final String optionA;
   private final String optionB;

   public CommunicationView(String comMsg, String optionA, String optionB) {
      this.comMsg = comMsg;
      this.optionA = optionA;
      this.optionB = optionB;
   }
   public CommunicationView(String comMsg) {
      this(comMsg, "", "");
   }

   public static CommunicationView withMessage(String message) {
      return new CommunicationView(message, "", "");
   }

   public static CommunicationView withOptionA(String optionA) {
      return new CommunicationView("", optionA, "");
   }

   public static CommunicationView withOptions(String optionA, String optionB) {
      return new CommunicationView("", optionA, optionB);
   }

   public String asFormattedMessage() {
      if (StringUtils.isNotEmpty(getComMsg())) {
         return getComMsg();
      } else if (StringUtils.isNotEmpty(getOptionA()) && StringUtils.isNotEmpty(getOptionB())) {
         return "Option 1: " + getOptionA() + "\nOption 2: " + getOptionB();
      } else if (StringUtils.isNotEmpty(getOptionA())) {
         return getOptionA();
      } else {
         return "";
      }
   }

   public String getComMsg() {
      return comMsg;
   }

   public String getOptionA() {
      return optionA;
   }

   public String getOptionB() {
      return optionB;
   }
}
