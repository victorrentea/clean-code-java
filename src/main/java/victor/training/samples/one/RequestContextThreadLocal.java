package victor.training.samples.one;

public class RequestContextThreadLocal {
   private static ThreadLocal<RequestContext> threadLocal = new ThreadLocal<>();
   public static RequestContext getRequestContext() {
      return threadLocal.get();
   }
}
