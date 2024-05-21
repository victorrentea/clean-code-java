package victor.training.cleancode.kata.projectservice;

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

   public ProjectUserDTO setRole(ProjectUserRoleType role) {
      this.role = role;
      return this;
   }

   public ProjectUserDTO setServices(List<String> services) {
      this.services = services;
      return this;
   }

   public ProjectUserDTO setUuid(String uuid) {
      this.uuid = uuid;
      return this;
   }
}
