package victor.training.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

   private final MessageSource messageSource;

   @ExceptionHandler(Exception.class)
   @ResponseStatus
   public String unexpected(Exception e,  HttpServletRequest request) {
      String userMessage = messageSource.getMessage("GENERAL",null, request.getLocale());

      log.error("Unexpected: "  +userMessage, e);
       return userMessage;
   }

   @ExceptionHandler(MyException.class)
   @ResponseStatus
   public String myexception(MyException e, HttpServletRequest request) {
      String userMessage = messageSource.getMessage(e.getCode().name(), e.getParams(), request.getLocale());
      log.error("Unexpected: " + userMessage, e);
       return userMessage;
   }
}

@RestController
class UnController {
   @GetMapping
   public void method() {
throw new IllegalArgumentException();
   }
}