package victor.training.cleancode.kata.projectservice;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
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
    User user = userService.findByUuid(projectUser.getUuid()).orElseThrow();
    List<Service> servicesToSend;
    if (projectUser.getRole() == ProjectUserRoleType.ADMIN) {
      servicesToSend = getSevicesForAdmin(project);
    } else {
      servicesToSend = getServicesForNonAdmin(projectUser, project);
    }
    for (Service service : servicesToSend) {
      ProjectServicesDTO dto = new ProjectServicesDTO(service);
      userServiceHelper.sendUserToServicesOnCreate(dto, project, messageAction, user, projectUser);
    }
  }

  private List<Service> getServicesForNonAdmin(ProjectUserDTO projectUser, Project project) {
    List<Service> servicesToSend;
    List<Service> userServices = serviceService.findAll()
        .stream().filter(s -> projectUser.getServices().contains(s.getName()))
        .toList();
    servicesToSend = new ArrayList<>();
    // iarta-ma dar aici am vrut sa iau toate proiectele userului din DB
    for (Service service : userServices) {
      projectServicesService.findByServiceAndProject(service, project)
          .filter(ProjectServices::isSubscribed)
          .ifPresent(s -> servicesToSend.add(service));
    }
    return servicesToSend;
  }

  private List<Service> getSevicesForAdmin(Project project) {
    return projectServicesService.getProjectServicesByProjectId(project.getId())
        .stream()
        .filter(ProjectServices::isSubscribed)
        .map(ProjectServices::getService)
        .collect(Collectors.toList());
  }

}
