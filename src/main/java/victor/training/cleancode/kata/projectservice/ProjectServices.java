package victor.training.cleancode.kata.projectservice;

public class ProjectServices {
   private ProjectServiceStatus projectServiceStatus;
   private Service service;

   public ProjectServiceStatus getProjectServiceStatus() {
      return projectServiceStatus;
   }

   public Service getService() {
      return service;
   }

   public ProjectServices setProjectServiceStatus(ProjectServiceStatus projectServiceStatus) {
      this.projectServiceStatus = projectServiceStatus;
      return this;
   }

   public ProjectServices setService(Service service) {
      this.service = service;
      return this;
   }
}
