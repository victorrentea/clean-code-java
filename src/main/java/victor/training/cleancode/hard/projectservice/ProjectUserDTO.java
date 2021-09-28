package victor.training.cleancode.hard.projectservice;

import java.util.List;

public class ProjectUserDTO {
   private ProjectUserRoleType role;
   private String uuid;
   private List<String> services;

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
