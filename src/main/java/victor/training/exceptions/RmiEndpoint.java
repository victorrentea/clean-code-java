package victor.training.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.decorator.Decorator;
import javax.interceptor.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.sql.SQLException;
import java.util.UUID;

@ExceptionLogging // all the methods in this class will be intercepted, caught and rethrown as manually did below
public class RmiEndpoint {
   private static final Logger log = LoggerFactory.getLogger(victor.training.exceptions.GlobalExceptionMapper.class);
   public String someMethodOnTheServer2() {
      return someBizMethod();
   }
   public String someMethodOnTheServer() {
      try {
         return someBizMethod(); // at the boundary / outskirts of the code. NOT in the biz logic .
      } catch (Exception e) {
         String id = UUID.randomUUID().toString();
         log.error("Booboo: " + e, e);
         throw new MyException("Error: check server for id " + id);
      }

   }

   private String someBizMethod() {
      throw new MyException(new SQLException());
   }
}

// If the bellow solution does not work for you, try using EJB Interceptors : https://docs.oracle.com/javaee/6/tutorial/doc/gkeed.html

// CDI interceptors : --------

@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@interface  ExceptionLogging {
}

@Interceptor
@ExceptionLogging
class ExceptionLoggerHandler implements Serializable {
   private static final Logger log = LoggerFactory.getLogger(ExceptionLoggerHandler.class);

   @AroundInvoke
   public Object computelatency(InvocationContext ctx) throws Exception{
      try {
         return ctx.proceed();
      } catch (Exception e) {
         String id = UUID.randomUUID().toString();
         log.error("Booboo: " + e, e);
         throw new MyException("Error: check server for id " + id);
      }

   }
}


