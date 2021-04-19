package victor.training.exceptions;

import victor.training.exceptions.MyException.ErrorCode;

import javax.inject.Inject;
import javax.ws.rs.GET;

public class SomeResource {
   @Inject
   OtherService otherService;
   @GET
   public void stuff() {
      otherService.stuff();
   }
}
// Domain ---------------------- below ------------------

class OtherService {

   public void stuff() {
      throw new MyException(ErrorCode.PHONE_NUMBER_ALREADY_EXISTS);
   }
}