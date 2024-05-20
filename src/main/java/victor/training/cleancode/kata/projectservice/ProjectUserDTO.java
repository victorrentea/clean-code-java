package victor.training.cleancode.kata.projectservice;

import lombok.Data;

import java.util.List;

@Data
public class ProjectUserDTO {
   private ProjectUserRoleType role;
   private String uuid;
   private List<String> services;
}
