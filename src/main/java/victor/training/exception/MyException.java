package victor.training.exception;

public class MyException extends  RuntimeException {

   public MyException(Exception e) {
      this(e, ErrorCode.GENERAL);
   }

   public MyException(String message, Exception cause) {
      this(message, cause, ErrorCode.GENERAL);
   }

   enum ErrorCode {
      GENERAL,
      BAD_CONFIG
   }

   private final ErrorCode code;
   private final Object[] params;

   public Object[] getParams() {
      return params;
   }

   public ErrorCode getCode() {
      return code;
   }



   public MyException(ErrorCode code, Object... params) {
      this.code = code;
      this.params = params;
   }

   public MyException(String message, ErrorCode code, Object... params) {
      super(message);
      this.code = code;
      this.params = params;
   }

   public MyException(String message, Throwable cause, ErrorCode code, Object... params) {
      super(message, cause);
      this.code = code;
      this.params = params;
   }

   public MyException(Throwable cause, ErrorCode code, Object... params) {
      super(cause);
      this.code = code;
      this.params = params;
   }

   public MyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ErrorCode code, Object... params) {
      super(message, cause, enableSuppression, writableStackTrace);
      this.code = code;
      this.params = params;
   }
}
