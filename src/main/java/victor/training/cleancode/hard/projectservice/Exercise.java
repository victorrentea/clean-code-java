package victor.training.cleancode.hard.projectservice;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import victor.training.cleancode.pretend.Service;

@Service
@RequiredArgsConstructor
public class Exercise {
   private ProjectServicesService projectServicesService;
   private UserService userService;
   private UserServiceHelper userServiceHelper;
   private ServiceService serviceService;

   public void sendUserMessageOnCreate(ProjectUserDTO projectUser, Project project, MessageAction messageAction, User principal) {
      if (projectUser.getRole().equals(ProjectUserRoleType.ADMIN)) {
         List<ProjectServices> projectServices = projectServicesService.getProjectServicesByProjectId(project.getId());
         List<ProjectServices> subscribedProjectServices = projectServices.stream()
             .filter(projectService -> projectService.getProjectServiceStatus().equals(ProjectServiceStatus.SUBSCRIBED))
             .collect(Collectors.toList());

         subscribedProjectServices.forEach(subscribedProjectService -> {
            ProjectServicesDTO projectServicesDTO = new ProjectServicesDTO();
            projectServicesDTO.setService(subscribedProjectService.getService());
            User user = userService.findByCuid(projectUser.getCuid()).get();
            userServiceHelper.sendUserToServicesOnCreate(projectServicesDTO, project, messageAction, user, principal, projectUser, ProjectUserRoleType.ADMIN.name());
         });
      } else {
         List<String> projectServices = projectUser.getServices();
         List<victor.training.cleancode.hard.projectservice.Service> services = serviceService.findAll();

         projectServices.forEach(pS -> services.forEach(service -> {
            if (service.getName().equals(pS)) {
               ProjectServices projectServices1 = projectServicesService.findByServiceAndProject(service, project);
               if (projectServices1 != null && projectServices1.getProjectServiceStatus().equals(ProjectServiceStatus.SUBSCRIBED)) {
                  ProjectServicesDTO projectServicesDTO = new ProjectServicesDTO();
                  projectServicesDTO.setService(service);
                  User user = userService.findByCuid(projectUser.getCuid()).get();
                  if (projectUser.getRole().equals(ProjectUserRoleType.VIEW)) {
                     userServiceHelper.sendUserToServicesOnCreate(projectServicesDTO, project, messageAction, user, principal, projectUser, ProjectUserRoleType.VIEW.name());
                  } else {
                     userServiceHelper.sendUserToServicesOnCreate(projectServicesDTO, project, messageAction, user, principal, projectUser, ProjectUserRoleType.CONTRIBUTOR.name());
                  }
               }
            }
         }));
      }
   }
}
