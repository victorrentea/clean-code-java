package victor.training.cleancode.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.net.URI;

@RequiredArgsConstructor
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

   private final MessageSource messageSource;

   @ExceptionHandler(Exception.class)
   @ResponseStatus
   public String handleAnyException(Exception e, HttpServletRequest request) {
      String userMessage = messageSource.getMessage("GENERAL", null, request.getLocale());

      log.error("Unexpected: " + userMessage, e);
      return userMessage;
   }

//   @ExceptionHandler(UserBlacklistedException.class)
//   public ResponseEntity<String> handleUserBlacklistedException(HttpServletRequest request) {
//      return ResponseEntity.status(302).location(new URI("...")).build();
//   }

   @ExceptionHandler(MyException.class) // more specific, attempted first
   @ResponseStatus
   public String handleMyException(MyException myException, HttpServletRequest request) {
//      String userMessage = messageSource.getMessage(
//          myException.getCode().name(),
//          myException.getParams(),
//          request.getLocale());
      log.error("Exception: " , myException);
      return myException.getCode().name();
   }
}
