package victor.training.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import victor.training.exceptions.MyException.ErrorCode;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Properties;

@Provider
public class MyExceptionMapper implements ExceptionMapper<MyException> {
   private static final Logger log = LoggerFactory.getLogger(MyExceptionMapper.class);
   Properties messageTranslations;
   @PostConstruct
   public void init() throws IOException {
      messageTranslations= new Properties();
      messageTranslations.load(MyExceptionMapper.class.getResourceAsStream("message.properties"));
   }
   @Override
   public Response toResponse(MyException exception) {
      log.error("Request finished with exception", exception);
      String messageKey = exception.getCode().name();
      String userMessage = messageTranslations.getProperty(messageKey);
      return Response.serverError().entity(userMessage).build();
   }
}