package victor.training.cleancode.kata.projectservice;

import lombok.RequiredArgsConstructor;
import victor.training.cleancode.kata.projectservice.ProjectServices.Status;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@RequiredArgsConstructor
public class Exercise {
   private final ProjectServicesService projectServicesService;
   private final UserService userService;
   private final UserServiceHelper userServiceHelper;
   private final ServiceService serviceService;

   public void sendUserMessageOnCreate(ProjectUserDTO projectUser, Project project, MessageAction messageAction) {
      if (projectUser.getRole() == ProjectUserRoleType.ADMIN) {
         List<ProjectServices> projectServices = projectServicesService.getProjectServicesByProjectId(project.getId());
         List<ProjectServices> subscribedProjectServices = projectServices.stream()
             .filter(projectService -> projectService.getStatus() == Status.SUBSCRIBED)
             .collect(Collectors.toList());

         subscribedProjectServices.forEach(subscribedProjectService -> {
            ProjectServicesDTO projectServicesDTO = new ProjectServicesDTO();
            projectServicesDTO.setService(subscribedProjectService.getService());
            User user = userService.findByUuid(projectUser.getUuid()).get();
            userServiceHelper.sendUserToServicesOnCreate(projectServicesDTO, project, messageAction, user, projectUser, ProjectUserRoleType.ADMIN.name());
         });
      } else {
         List<String> projectServices = projectUser.getServices();
         List<victor.training.cleancode.kata.projectservice.Service> services = serviceService.findAll();

         projectServices.forEach(pS -> services.forEach(service -> {
            if (service.getName().equals(pS)) {
               ProjectServices projectServices1 = projectServicesService.findByServiceAndProject(service, project);
               if (projectServices1 != null && projectServices1.getStatus() == Status.SUBSCRIBED) {
                  ProjectServicesDTO projectServicesDTO = new ProjectServicesDTO();
                  projectServicesDTO.setService(service);
                  User user = userService.findByUuid(projectUser.getUuid()).get();
                  if (projectUser.getRole() == ProjectUserRoleType.VIEW) {
                     userServiceHelper.sendUserToServicesOnCreate(projectServicesDTO, project, messageAction, user, projectUser, ProjectUserRoleType.VIEW.name());
                  } else {
                     userServiceHelper.sendUserToServicesOnCreate(projectServicesDTO, project, messageAction, user, projectUser, ProjectUserRoleType.CONTRIBUTOR.name());
                  }
               }
            }
         }));
      }
   }
}
