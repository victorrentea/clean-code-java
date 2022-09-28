package victor.training.cleancode.extra.kata.projectservice;

import java.util.List;

public class ProjectUserDto {
   private ProjectUserRoleType role;
   private String uuid;
   private List<String> services;

   public boolean hasService(Service service) {
       return getServices().contains(service.getName());
   }

   public ProjectUserRoleType getRole() {
      return role;
   }

   public String getUuid() {
      return uuid;
   }

   public List<String> getServices() {
      return services;
   }
}
