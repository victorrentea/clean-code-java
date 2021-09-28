package victor.training.cleancode.hard.projectservice;

public class ProjectServices {
   private ProjectServiceStatus projectServiceStatus;
   private Service service;

   public ProjectServiceStatus getProjectServiceStatus() {
      return projectServiceStatus;
   }

   public void setProjectServiceStatus(ProjectServiceStatus projectServiceStatus) {
      this.projectServiceStatus = projectServiceStatus;
   }

   public Service getService() {
      return service;
   }

   public void setService(Service service) {
      this.service = service;
   }
}
