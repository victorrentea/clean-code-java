package victor.training.cleancode.extra.kata.projectservice;

public class ProjectServiceDto {
   private final Service service;

   public ProjectServiceDto(Service service) {
      this.service = service;
   }

   public Service getService() {
      return service;
   }
}
