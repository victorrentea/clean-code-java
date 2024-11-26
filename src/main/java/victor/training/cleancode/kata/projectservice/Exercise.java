package victor.training.cleancode.kata.projectservice;

import lombok.RequiredArgsConstructor;
import victor.training.cleancode.kata.projectservice.ProjectServices.Status;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@RequiredArgsConstructor
public class Exercise {
   private final ProjectServicesRepo projectServicesRepo;
   private final UserRepo userRepo;
   private final ServiceRepo serviceRepo;
   private final UserServiceHelper userServiceHelper;

   public void sendUserMessageOnCreate(UserDto userDto, Project project, MessageAction action) {
      if (userDto.getRole() == UserRole.ADMIN) {
         List<ProjectServices> projectServices = projectServicesRepo.findByProjectId(project.getId());
         List<ProjectServices> subscribedProjectServices = projectServices.stream()
             .filter(projectService -> projectService.getStatus() == Status.SUBSCRIBED)
             .collect(Collectors.toList());

         subscribedProjectServices.forEach(subscribedProjectService -> {
            ProjectServicesDto projectServicesDto = new ProjectServicesDto();
            projectServicesDto.setService(subscribedProjectService.getService());
            User user = userRepo.findByUuid(userDto.getUuid()).get();
            userServiceHelper.sendUserToServicesOnCreate(projectServicesDto, project, action, user, userDto, UserRole.ADMIN.name());
         });
      } else {
         List<String> projectServices = userDto.getServices();
         List<victor.training.cleancode.kata.projectservice.Service> services = serviceRepo.findAll();

         projectServices.forEach(pS -> services.forEach(service -> {
            if (service.getName().equals(pS)) {
               ProjectServices projectServices1 = projectServicesRepo.findByServiceAndProject(service, project);
               if (projectServices1 != null && projectServices1.getStatus() == Status.SUBSCRIBED) {
                  ProjectServicesDto projectServicesDto = new ProjectServicesDto();
                  projectServicesDto.setService(service);
                  User user = userRepo.findByUuid(userDto.getUuid()).get();
                  if (userDto.getRole() == UserRole.VIEW) {
                     userServiceHelper.sendUserToServicesOnCreate(projectServicesDto, project, action, user, userDto, UserRole.VIEW.name());
                  } else {
                     userServiceHelper.sendUserToServicesOnCreate(projectServicesDto, project, action, user, userDto, UserRole.CONTRIBUTOR.name());
                  }
               }
            }
         }));
      }
   }
}
