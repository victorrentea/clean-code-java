package victor.training.exceptions;

import java.sql.SQLException;

class MyIncrediblyRareRecoverableException extends MyException {

   public MyIncrediblyRareRecoverableException(ErrorCode code) {
      super(ErrorCode.RECOVERABLE_WOW);
   }
}
/// somewhere in the code catch (MyIncrediblyRareRecoverableException e) {    }
public class MyException extends RuntimeException {
   public MyException(Throwable cause) {
      super(cause);
      code = ErrorCode.GENERAL;
   }

   // THE ONLY EXCEPTON IN MY APP
   public enum ErrorCode {
      PHONE_NUMBER_ALREADY_EXISTS,
      ORDER_ALREADY_CANCELLED,
      RECOVERABLE_WOW,
      GENERAL
   }
   private final ErrorCode code;

   public MyException(ErrorCode code) {
      this.code = code;
   }

   public MyException(String message) {
      this(message, ErrorCode.GENERAL);
   }
   public MyException(String message, ErrorCode code) {
      super(message);
      this.code = code;
   }

   public MyException(String message, Throwable cause, ErrorCode code) {
      super(message, cause);
      this.code = code;
   }

   public MyException(Throwable cause, ErrorCode code) {
      super(cause);
      this.code = code;
   }

   public MyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ErrorCode code) {
      super(message, cause, enableSuppression, writableStackTrace);
      this.code = code;
   }

   public ErrorCode getCode() {
      return code;
   }
}
// imagine a properties file translating
// CODE1=bla bla bla
// CODE2=bla bla bla
