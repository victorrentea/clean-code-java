package victor.training.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {
   private static final Logger log = LoggerFactory.getLogger(GlobalExceptionMapper.class);
   @Override
   public Response toResponse(Exception exception) {
      log.error("Request finished with exception", exception);
      return Response.serverError().entity("Oups! It happens. Check the logs.").build();
   }
}