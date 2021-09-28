package victor.training.cleancode.hard.projectservice;

import java.util.List;

public class ProjectUserDTO {
   private ProjectUserRoleType role;

   public ProjectUserRoleType getRole() {
      return role;
   }

   public void setRole(ProjectUserRoleType role) {
      this.role = role;
   }

   public String getCuid() {
      return null;
   }

   public List<String> getServices() {
      return null;
   }
}
