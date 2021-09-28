package victor.training.cleancode.hard.projectservice;

import java.util.List;

public class ProjectUserDTO {
   private ProjectUserRoleType role;
   private String uuid;

   public ProjectUserRoleType getRole() {
      return role;
   }

   public void setRole(ProjectUserRoleType role) {
      this.role = role;
   }

   public String getUuid() {
      return uuid;
   }

   public void setUuid(String uuid) {
      this.uuid = uuid;
   }

   public List<String> getServices() {
      return null;
   }
}
