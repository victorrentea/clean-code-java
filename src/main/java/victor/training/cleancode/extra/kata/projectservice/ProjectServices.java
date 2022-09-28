package victor.training.cleancode.extra.kata.projectservice;

public class ProjectServices {
   private ProjectServiceStatus projectServiceStatus;
   private Service service;

   public boolean isSubscribed() {
      return projectServiceStatus == ProjectServiceStatus.SUBSCRIBED;
   }

   public ProjectServiceStatus getProjectServiceStatus() {
      return projectServiceStatus;
   }


   public Service getService() {
      return service;
   }

}
