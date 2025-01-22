package victor.training.cleancode.java.kata.projectservice;

public class ProjectServices {
   private Status status;
   private Service service;

   public Status getStatus() {
      return status;
   }

   public Service getService() {
      return service;
   }

   public ProjectServices setStatus(Status status) {
      this.status = status;
      return this;
   }

   public ProjectServices setService(Service service) {
      this.service = service;
      return this;
   }

   public enum Status {
      DRAFT,
      CREATED,
      SUBSCRIBED
   }
}
