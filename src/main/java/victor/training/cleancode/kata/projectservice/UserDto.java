package victor.training.cleancode.kata.projectservice;

import java.util.List;

public class UserDto {
   private UserRole role;
   private String uuid;
   private List<String> services;

   public UserRole getRole() {
      return role;
   }

   public String getUuid() {
      return uuid;
   }

   public List<String> getServices() {
      return services;
   }

   public UserDto setRole(UserRole role) {
      this.role = role;
      return this;
   }

   public UserDto setServices(List<String> services) {
      this.services = services;
      return this;
   }

   public UserDto setUuid(String uuid) {
      this.uuid = uuid;
      return this;
   }
}
