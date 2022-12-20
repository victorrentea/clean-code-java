package victor.training.cleancode.exception;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.meta.TypeQualifierNickname;
import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

   private final MessageSource messageSource;

   @ExceptionHandler(Exception.class)
   @ResponseStatus
   public String handleAnyException(Exception e, HttpServletRequest request) {
      String userMessage = messageSource.getMessage("GENERAL", null, request.getLocale());
//      Try<String> success = Try.of("aa");
//      Try<String> err = Try.failure(new IllegalArgumentException();());
      log.error("Unexpected: " + userMessage, e);
      return userMessage;
   }

   @ExceptionHandler(MyException.class) // more specific, attempted first
   @ResponseStatus
   public String handleMyException(MyException myException, HttpServletRequest request) {
//      switch (myException.getCode()) {
//         case BAD_CONFIG -> send message
//      }
      String userMessage = messageSource.getMessage(
          myException.getCode().name(),
          myException.getParams(),
          request.getLocale());
      log.error("Exception: " + userMessage, myException);
      return userMessage;
   }
}
