package victor.training.cleancode.extra.kata.projectservice;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static victor.training.cleancode.extra.kata.projectservice.ProjectUserRoleType.*;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class Exercise {
    private final ProjectServicesService projectServicesService;
    private final UserService userService;
    private final UserServiceHelper userServiceHelper;
    private final ServiceService serviceService;

    public void sendUserMessageOnCreate(ProjectUserDto projectUser, Project project, MessageAction messageAction) {
        List<Service> servicesToSend = determineServicesToSend(projectUser, project);
        User user = userService.findByUuid(projectUser.getUuid()).orElseThrow();
        sendServices(projectUser, project, messageAction, servicesToSend, user);
    }

    private void sendServices(ProjectUserDto projectUser, Project project, MessageAction messageAction, List<Service> servicesToSend, User user) {
        List<ProjectServiceDto> dtos = servicesToSend.stream().map(ProjectServiceDto::new).collect(toList());
        for (ProjectServiceDto dto : dtos) {
            userServiceHelper.sendUserToServicesOnCreate(dto, project, messageAction, user, projectUser);
        }
    }

    @NotNull
    private List<Service> determineServicesToSend(ProjectUserDto projectUser, Project project) {
        if (projectUser.getRole() == ADMIN) {
            return projectServicesService.getProjectServicesByProjectId(project.getId()).stream()
                    .filter(ProjectServices::isSubscribed)
                    .map(ProjectServices::getService)
                    .collect(toList());
        } else {
            return serviceService.findAll().stream()
                    .filter(projectUser::hasService)
                    .filter(service -> hasSubscribedServices(project, service))
                    .collect(toList());

        }
    }

    private boolean hasSubscribedServices(Project project, Service service) {
        return projectServicesService.findByServiceAndProject(service, project)
                .filter(ProjectServices::isSubscribed)
                .isPresent();
    }

}
